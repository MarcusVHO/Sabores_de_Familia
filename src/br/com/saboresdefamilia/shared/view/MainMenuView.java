package br.com.saboresdefamilia.shared.view;

import br.com.saboresdefamilia.customer.view.CustomerMenuView;

import java.util.Scanner;

public class MainMenuView extends MenuView {

    protected void showTitle() {
        System.out.println(
                    """
                     ███████╗ █████╗ ██████╗  ██████╗ ██████╗ ███████╗███████╗           \s
                     ██╔════╝██╔══██╗██╔══██╗██╔═══██╗██╔══██╗██╔════╝██╔════╝           \s
                     ███████╗███████║██████╔╝██║   ██║██████╔╝█████╗  ███████╗           \s
                     ╚════██║██╔══██║██╔══██╗██║   ██║██╔══██╗██╔══╝  ╚════██║           \s
                     ███████║██║  ██║██████╔╝╚██████╔╝██║  ██║███████╗███████║           \s
                     ╚══════╝╚═╝  ╚═╝╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝           \s
                   \s
                     ██████╗ ███████╗    ███████╗ █████╗ ███╗   ███╗██╗██╗     ██╗ █████╗\s
                     ██╔══██╗██╔════╝    ██╔════╝██╔══██╗████╗ ████║██║██║     ██║██╔══██╗
                     ██║  ██║█████╗      █████╗  ███████║██╔████╔██║██║██║     ██║███████║
                     ██║  ██║██╔══╝      ██╔══╝  ██╔══██║██║╚██╔╝██║██║██║     ██║██╔══██║
                     ██████╔╝███████╗    ██║     ██║  ██║██║ ╚═╝ ██║██║███████╗██║██║  ██║
                     ╚═════╝ ╚══════╝    ╚═╝     ╚═╝  ╚═╝╚═╝     ╚═╝╚═╝╚══════╝╚═╝╚═╝  ╚═╝
                   """);
    }

    protected void showOptions() {
        System.out.println(
                """
               1 - Pedidos
               2 - Clientes
               3 - Cardapios
               4 - Tamanhos
               5 - Sair
               """
        );
    }

    protected void router(int option) {
        switch (option) {
            case 1:
                System.out.println("Pedidos");
                break;
            case 2:
                CustomerMenuView customerMenuView = new CustomerMenuView();
                customerMenuView.showMenu();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                System.out.println("Saindo ............");
                exit();
                break;
            default:
                System.out.println("Opcao invalida!");
                break;
        }
    }

}
