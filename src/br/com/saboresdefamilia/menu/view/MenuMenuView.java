package br.com.saboresdefamilia.menu.view;

import br.com.saboresdefamilia.shared.view.MenuView;

public class MenuMenuView extends MenuView {

    @Override
    protected void showTitle() {
        System.out.println(
                """
                 ██████╗ █████╗ ██████╗ ██████╗  █████╗ ██████╗ ██╗ ██████╗ ███████╗
                ██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔══██╗██║██╔═══██╗██╔════╝
                ██║     ███████║██████╔╝██║  ██║███████║██████╔╝██║██║   ██║███████╗
                ██║     ██╔══██║██╔══██╗██║  ██║██╔══██║██╔═══╝ ██║██║   ██║╚════██║
                ╚██████╗██║  ██║██║  ██║██████╔╝██║  ██║██║     ██║╚██████╔╝███████║
                 ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝ ╚═════╝ ╚══════╝
                """
        );
    }

    @Override
    protected void showOptions() {
        System.out.println(
                """
                1 - Adicionar Cardapio
                2 - Exibir Cardapios
                3 - Editar Cardapio
                4 - Deletar Cardapio
                0 - Sair
                """
        );
    }

    @Override
    protected void router(int option) {
        switch (option) {
            case 1:
                MenuFormView menuFormView = new MenuFormView();
                menuFormView.addingNewMenu();
                break;
            case 2:
                MenuShowView menuShowView = new MenuShowView();
                menuShowView.showingMenus();
                break;
            case 3:
                MenuEditView menuEditView = new MenuEditView();
                menuEditView.editing();
                break;
            case 4:
                MenuDeleteView menuDeleteView = new MenuDeleteView();
                menuDeleteView.deletingMenu();
                break;
            case 0:
                this.exit();
        }
    }
}
