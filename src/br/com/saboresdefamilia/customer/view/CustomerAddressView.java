package br.com.saboresdefamilia.customer.view;

import br.com.saboresdefamilia.customer.core.CustomerCore;
import br.com.saboresdefamilia.customer.model.Address;
import br.com.saboresdefamilia.shared.util.ConsoleUtils;
import br.com.saboresdefamilia.shared.view.BaseView;


public class CustomerAddressView extends BaseView {
    private final CustomerCore customerCore;
    private boolean editingAddress = true;

    public CustomerAddressView(CustomerCore customerCore) {
        this.customerCore = customerCore;
    }


    private void showOptionsAddress() {
        System.out.println("1 - Adicionar Endereco | 2 - Editar Endereco | 3 -  Deletar Endereco | 4 - Confirmar");
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
        Address address = customerCore.getAddress(readInt("Digite o index do endereço: "));
        if(address == null) {
            return;
        }
        boolean editingAddress = true;
        while (editingAddress) {
            ConsoleUtils.clear();
            System.out.println(address);
            showOptionsEditAddress();
            int option = readInt("Digite a opção desejada:");

            switch (option) {
                case 1:
                    address.setAddress(readString("Rua: "));
                    break;
                case 2:
                    address.setNumber(readInt("Numero: "));
                    break;
                case 3:
                    address.setComplement(readString("Complemento: "));
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
                readString("Rua: "),
                readInt("Numero: "),
                readString("Complemento: ")
        );
    }

    private void removeAddress() {
        customerCore.removeAddressInListAddress(readInt("Digite o index do endereço: "));
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
            listObjects(customerCore.getAddresses(), "Endereços");
            showOptionsAddress();
            int option = readInt("Digite a opção desejada:");
            routerAddress(option);
        }

    }


}
