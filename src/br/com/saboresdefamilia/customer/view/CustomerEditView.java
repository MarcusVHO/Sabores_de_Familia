package br.com.saboresdefamilia.customer.view;

import br.com.saboresdefamilia.customer.core.CustomerCore;
import br.com.saboresdefamilia.customer.model.Customer;
import br.com.saboresdefamilia.shared.util.ConsoleUtils;
import br.com.saboresdefamilia.shared.view.BaseView;

import java.util.Objects;

public class CustomerEditView extends BaseView {
    private final CustomerCore customerCore = new CustomerCore();
    private final CustomerAddressView customerAddressView = new CustomerAddressView(customerCore);
    private final CustomerPhoneView customerPhoneView = new CustomerPhoneView(customerCore);

    private void showOptions() {
        System.out.println(
                """
                1 - Editar Nome
                2 - Editar Descricao
                3 - Editar Enderecos
                4 - Editar Telefones
                5 - Confirmar
                0 - Cancelar
                """
        );
    }

    public void editCustomer() {
        ConsoleUtils.clear();
        String searchName = readString("Digite o nome ao qual deseja editar (Deixe em branco para sair): ");

        if (Objects.equals(searchName, "")) {
            return;
        }

        customerCore.searchCustomerByName(searchName);
        listObjects(customerCore.getResultSearchCustomers(), "Clientes");

        Integer index = readInt("Digite o index do cliente que deseja editar (Deixe em branco para sair): ");

        if(index == null) {
            return;
        }

        Customer customer = customerCore.getCustomerinSearchList(index);
        customerCore.setCurrentEditCustomer(customer);
        customerCore.setAddresses(customerCore.getCompleteCustomerDTO().getAddresses());
        customerCore.setPhones(customerCore.getCompleteCustomerDTO().getPhones());

        System.out.println(customer);

        editing();
    }

    private void editName() {
        String newName = readString("Digite o novo nome: ");
        customerCore.getCompleteCustomerDTO().getCustomer().setName(newName);
    }

    private void editDescription() {
        String newDescription = readString("Digite a nova descrição do cliente: ");
        customerCore.getCompleteCustomerDTO().getCustomer().setDescription(newDescription);
    }




    private void editing() {
        while (true) {
            showOptions();
            int option = readInt("Digite a opcao desejada: ");
            switch (option) {
                case 1:
                    editName();
                    break;
                case 2:
                    editDescription();
                    break;
                case 3:
                    customerAddressView.show();
                    customerCore.getCompleteCustomerDTO().setAddresses(customerCore.getAddresses());
                    break;
                case 4:
                    customerPhoneView.show();
                    customerCore.getCompleteCustomerDTO().setPhones(customerCore.getPhones());
                    break;
                case 5:
                    customerCore.updateUser();
                case 0:
                    return;

                default:
                    System.out.println("Opcao Invalida!!");
                    break;

            }
        }
    }
}
