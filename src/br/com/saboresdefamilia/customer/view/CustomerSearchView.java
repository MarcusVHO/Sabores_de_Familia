package br.com.saboresdefamilia.customer.view;

import br.com.saboresdefamilia.customer.core.CustomerCore;
import br.com.saboresdefamilia.shared.util.ConsoleUtils;
import br.com.saboresdefamilia.shared.view.BaseView;

import java.util.Objects;

public class CustomerSearchView extends BaseView {
    private final CustomerCore customerCore = new CustomerCore();

    public void searchCustomer() {
        while (true) {
            ConsoleUtils.clear();
            listObjects(customerCore.getResultSearchCustomers(), "Clientes");
            String searchName = readString("Digite o nome ao qual deseja pesquisar (Deixe em branco para sair): ");
            if (Objects.equals(searchName, "")) {
                return;
            }
            customerCore.searchCustomerByName(searchName);
        }

    }
}
