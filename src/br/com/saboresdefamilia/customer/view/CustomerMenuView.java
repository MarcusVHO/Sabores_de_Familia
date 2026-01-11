package br.com.saboresdefamilia.customer.view;

import br.com.saboresdefamilia.shared.view.MenuView;

import java.util.Scanner;

public class CustomerMenuView extends MenuView {
    protected void showTitle() {
        System.out.println(
                """
                 ██████╗██╗     ██╗███████╗███╗   ██╗████████╗███████╗███████╗
                ██╔════╝██║     ██║██╔════╝████╗  ██║╚══██╔══╝██╔════╝██╔════╝
                ██║     ██║     ██║█████╗  ██╔██╗ ██║   ██║   █████╗  ███████╗
                ██║     ██║     ██║██╔══╝  ██║╚██╗██║   ██║   ██╔══╝  ╚════██║
                ╚██████╗███████╗██║███████╗██║ ╚████║   ██║   ███████╗███████║
                 ╚═════╝╚══════╝╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚══════╝╚══════╝
                """
        );
    }

    protected void  showOptions() {
        System.out.println(
                """
               1 - Cadastrar Cliente
               2 - Pesquisar Cliente
               3 - Editar Cliente
               4 - Voltar Para o Menu Principal
               """
        );
    }

    protected void router(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Cadastrar Cliente");
                CustomerFormView customerFormView = new CustomerFormView();
                customerFormView.registerNewCustomer();
                break;

            case 2:
                System.out.println("Pesquisar Cliente");
                break;

            case 3:
                System.out.println("Editar Cliente");
                break;

            case 4:
                System.out.println("Voltar Para o Menu Principal");
                exit();
                break;

            default:
                System.out.println("Opção Invalida..");
                break;

        }
    }
}
