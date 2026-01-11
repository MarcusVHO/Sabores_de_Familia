package br.com.saboresdefamilia.customer.view;

import br.com.saboresdefamilia.customer.core.CustomerCore;
import br.com.saboresdefamilia.customer.model.Phone;
import br.com.saboresdefamilia.shared.util.ConsoleUtils;
import br.com.saboresdefamilia.shared.view.BaseView;



public class CustomerPhoneView extends BaseView {
    private final CustomerCore customerCore;
    private boolean editingPhone = true;

    public CustomerPhoneView(CustomerCore customerCore) {
        this.customerCore = customerCore;
    }

    private void showOptionsPhone() {
        System.out.println("1 - Adicionar Telefone | 2 - Editar Telefone | 3 -  Deletar Telefone | 4 - Confirmar");
    }

    private void editPhone() {
        Phone phone = customerCore.getPhone(readInt("Digite o index do endereço: "));
        if (phone == null) {
            return;
        }
        phone.setPhone(readInt("Numero de Telefone:"));
    }

    private void addPhone() {
        customerCore.createNewPhoneInListPhones(readInt("Numero de Telefone:"));
    }

    private void removePhone() {
        customerCore.removePhoneInListPhones(readInt("Digite o index do endereço: "));
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
            listObjects(customerCore.getPhones(), "Telefones");
            showOptionsPhone();
            int option = readInt("Digite a opcão desejada: ");
            routerPhone(option);
        }

    }

}
