package br.com.saboresdefamilia.customer.view;
import br.com.saboresdefamilia.customer.core.CustomerCore;
import java.util.Scanner;

public class CustomerFormView {
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

    private String readName() {
        System.out.print("Nome: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private String readDescription() {
        System.out.print("Descricao: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void createNewCustomer(){
        String name = readName();
        String description =  readDescription();
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
