package br.com.saboresdefamilia.customer.view;

import br.com.saboresdefamilia.customer.core.CustomerCore;
import br.com.saboresdefamilia.customer.model.Address;
import br.com.saboresdefamilia.shared.util.ConsoleUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerAddressView {
    private final CustomerCore customerCore;
    private boolean editingAddress = true;

    public CustomerAddressView(CustomerCore customerCore) {
        this.customerCore = customerCore;
    }


    private void listItems(ArrayList<Address> addresses) {
        System.out.println("======================== Enderecos ========================");
        if (!addresses.isEmpty()) {
            for (Address address : addresses) {
                System.out.println(addresses.indexOf(address) + " - " + address);
            }
        }
        System.out.println("===========================================================");
    }

    private void showOptionsAddress() {
        System.out.println("1 - Adicionar Endereco | 2 - Editar Endereco | 3 -  Deletar Endereco | 4 - Confirmar");
    }

    private int readOption() {
        System.out.println("Digite a opção desejada:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private String readRoad() {
        System.out.println("Rua: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    private int readNumber() {
        System.out.println("Numero: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    private String readComplement() {
        System.out.println("Complemento: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private int readIndex() {
        System.out.println("Digite o index do endereço: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void showOptionsEditAddress() {
        System.out.println(
                """
                1 - Editar Rua
                2 - Editar Numero
                3 - Editar Complemento
                4 - Confirmar
                """
        );
    }

    private void editAddress() {
        Address address = customerCore.getAddress(readIndex());
        if(address == null) {
            return;
        }
        boolean editingAddress = true;
        while (editingAddress) {
            ConsoleUtils.clear();
            System.out.println(address);
            showOptionsEditAddress();
            int option = readOption();

            switch (option) {
                case 1:
                    address.setAddress(readRoad());
                    break;
                case 2:
                    address.setNumber(readNumber());
                    break;
                case 3:
                    address.setComplement(readComplement());
                    break;
                case 4:
                    editingAddress = false;
                    break;
                default:
                    System.out.println("Opcao invalida! ");
                    break;

            }

        }
    }

    private void addAddress() {
        customerCore.createNewAddressInListAddresses(
                readRoad(),
                readNumber(),
                readComplement()
        );
    }

    private void removeAddress() {
        customerCore.removeAddressInListAddress(readIndex());
    }

    private void routerAddress(int option) {
        switch (option) {
            case 1:
                addAddress();
                break;
            case 2:
                editAddress();
                break;
            case 3:
                removeAddress();
                break;
            case 4:
                editingAddress = false;
                break;
            default:
                System.out.println("Opcao invalida!");
                break;
        }

    }

    public void show() {
        while (editingAddress) {
            ConsoleUtils.clear();
            listItems(customerCore.getAddresses());
            showOptionsAddress();
            int option = readOption();
            routerAddress(option);
        }

    }


}
