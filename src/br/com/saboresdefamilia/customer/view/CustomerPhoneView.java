package br.com.saboresdefamilia.customer.view;

import br.com.saboresdefamilia.customer.core.CustomerCore;
import br.com.saboresdefamilia.customer.model.Phone;
import br.com.saboresdefamilia.shared.util.ConsoleUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerPhoneView {
    private final CustomerCore customerCore;
    private boolean editingPhone = true;

    public CustomerPhoneView(CustomerCore customerCore) {
        this.customerCore = customerCore;
    }

    private void listItems(ArrayList<Phone> phones) {
        System.out.println("======================== Phone ========================");
        if (!phones.isEmpty()) {
            for (Phone phone : phones) {
                System.out.println(phones.indexOf(phone) + " - " + phone);
            }
        }
        System.out.println("========================================================");
    }

    private void showOptionsPhone() {
        System.out.println("1 - Adicionar Telefone | 2 - Editar Telefone | 3 -  Deletar Telefone | 4 - Confirmar");
    }

    private int readOption() {
        System.out.println("Digite a opção desejada: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private int readPhone(){
        System.out.println("Numero de Telefone: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private int readIndex() {
        System.out.println("Digite o index do endereço: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void editPhone() {
        Phone phone = customerCore.getPhone(readIndex());
        if (phone == null) {
            return;
        }
        phone.setPhone(readPhone());
    }

    private void addPhone() {
        customerCore.createNewPhoneInListPhones(readPhone());
    }

    private void removePhone() {
        customerCore.removePhoneInListPhones(readIndex());
    }

    private void routerPhone(int option) {
        switch (option) {
            case 1:
                addPhone();
                break;
            case 2:
                editPhone();
                break;
            case 3:
                removePhone();
                break;
            case 4:
                editingPhone = false;
                break;
            default:
                System.out.println("Opcao invalida!");
                break;
        }
    }

    public void show() {
        while (editingPhone) {
            ConsoleUtils.clear();
            listItems(customerCore.getPhones());
            showOptionsPhone();
            int option = readOption();
            routerPhone(option);
        }

    }

}
