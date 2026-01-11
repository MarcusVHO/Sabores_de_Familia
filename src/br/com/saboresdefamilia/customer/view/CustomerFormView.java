package br.com.saboresdefamilia.customer.view;
import br.com.saboresdefamilia.customer.core.CustomerCore;
import br.com.saboresdefamilia.shared.view.BaseView;


public class CustomerFormView extends BaseView {
    private final CustomerCore customerCore = new CustomerCore();
    private final CustomerAddressView customerAddressView = new CustomerAddressView(customerCore);
    private final CustomerPhoneView customerPhoneView = new CustomerPhoneView(customerCore);

    private void showTitle() {
        System.out.println(
                 """
                 ██████╗ █████╗ ██████╗ ███████╗████████╗██████╗  █████╗ ██████╗      ██████╗██╗     ██╗███████╗███╗   ██╗████████╗███████╗███████╗
                ██╔════╝██╔══██╗██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██╔══██╗    ██╔════╝██║     ██║██╔════╝████╗  ██║╚══██╔══╝██╔════╝██╔════╝
                ██║     ███████║██║  ██║███████╗   ██║   ██████╔╝███████║██████╔╝    ██║     ██║     ██║█████╗  ██╔██╗ ██║   ██║   █████╗  ███████╗
                ██║     ██╔══██║██║  ██║╚════██║   ██║   ██╔══██╗██╔══██║██╔══██╗    ██║     ██║     ██║██╔══╝  ██║╚██╗██║   ██║   ██╔══╝  ╚════██║
                ╚██████╗██║  ██║██████╔╝███████║   ██║   ██║  ██║██║  ██║██║  ██║    ╚██████╗███████╗██║███████╗██║ ╚████║   ██║   ███████╗███████║
                 ╚═════╝╚═╝  ╚═╝╚═════╝ ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝     ╚═════╝╚══════╝╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝╚══════╝
               """);
    }


    private void createNewCustomer(){
        String name = readString("Nome: ");
        String description =  readString("Descricao: ");
        customerCore.createNewCustomer(name, description);
    }


    public void registerNewCustomer() {
        showTitle();
        createNewCustomer();
        customerAddressView.show();
        customerPhoneView.show();
        customerCore.registerNewCustomerInDb();
    }

}
