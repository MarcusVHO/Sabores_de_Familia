package br.com.saboresdefamilia.menu.view;

import br.com.saboresdefamilia.menu.core.MenuEditCore;
import br.com.saboresdefamilia.menu.model.Menu;
import br.com.saboresdefamilia.menu.model.MenuItem;
import br.com.saboresdefamilia.shared.util.ConsoleUtils;
import br.com.saboresdefamilia.shared.view.BaseView;

import java.util.ArrayList;

public class MenuEditView extends BaseView {
    private ArrayList<MenuItem> menuItems;
    private Menu menu;

    MenuEditCore menuEditCore = new MenuEditCore();

    private void showTitle() {
        System.out.println(
                """
                ███████╗██████╗ ██╗████████╗ █████╗ ██████╗      ██████╗ █████╗ ██████╗ ██████╗  █████╗ ██████╗ ██╗ ██████╗
                ██╔════╝██╔══██╗██║╚══██╔══╝██╔══██╗██╔══██╗    ██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔══██╗██║██╔═══██╗
                █████╗  ██║  ██║██║   ██║   ███████║██████╔╝    ██║     ███████║██████╔╝██║  ██║███████║██████╔╝██║██║   ██║
                ██╔══╝  ██║  ██║██║   ██║   ██╔══██║██╔══██╗    ██║     ██╔══██║██╔══██╗██║  ██║██╔══██║██╔═══╝ ██║██║   ██║
                ███████╗██████╔╝██║   ██║   ██║  ██║██║  ██║    ╚██████╗██║  ██║██║  ██║██████╔╝██║  ██║██║     ██║╚██████╔╝
                ╚══════╝╚═════╝ ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝     ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝ ╚═════╝
                """
        );
    }


    private void selectMenu() {
        while (true) {
            listObjects(menuEditCore.getResultSearchMenus(), "Cardapios");
            Integer index = readInt("Digite o index do Cardapio que deseja editar (deixe em branco para sair): ");

            if (index == null) {
                return;
            }

            if (menuEditCore.getResultSearchMenus().size()< index+1) {
                ConsoleUtils.clear();
                continue;
            }

            menu = menuEditCore.getResultSearchMenus().get(index).getMenu();
            menuItems = menuEditCore.getResultSearchMenus().get(index).getMenuItems();
            break;
        }
    }

    private void showOptions() {
        System.out.println(
                """
                1 - Editar Nome
                2 - Editar Descricao
                3 - Editar Itens
                4 - Confirmar
                0 - Sair
                """
        );
    }

    private void editName() {
        String name = readString("Digite o novo nome: ");
        menu.setName(name);
    }

    private void editDescription() {
        String description = readString("Digite a nova descricao: ");
        menu.setDescription(description);
    }


    public void editing() {
        menuEditCore.searchMenus();
        ConsoleUtils.clear();
        showTitle();
        selectMenu();

        while (true) {
            ConsoleUtils.clear();
            showTitle();
            System.out.println("||  " + menu + "  || \n");
            showOptions();
            Integer option = readInt("Digite a opcao desejada: ");

            if (option == null || option == 0) {
                return;
            }

            switch (option) {
                case 1:
                    editName();
                    break;
                case 2:
                    editDescription();
                    break;
                case 3:
                    MenuItemsView menuItemsView = new MenuItemsView(menuItems);
                    menuItemsView.editingMenu();
                    break;
                case 4:
                    menuEditCore.updateMenu(menu, menuItems);
                    return;

                default:
                    System.out.println("Opcao invalida! ");
                    break;
            }
        }

    }

}
