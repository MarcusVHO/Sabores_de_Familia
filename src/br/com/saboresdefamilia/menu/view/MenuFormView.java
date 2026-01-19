package br.com.saboresdefamilia.menu.view;

import br.com.saboresdefamilia.menu.core.MenuFormCore;
import br.com.saboresdefamilia.menu.model.Menu;
import br.com.saboresdefamilia.menu.model.MenuItem;
import br.com.saboresdefamilia.shared.util.ConsoleUtils;
import br.com.saboresdefamilia.shared.view.BaseView;

import java.util.ArrayList;

public class MenuFormView extends BaseView {
    private final ArrayList<MenuItem> menuItems = new ArrayList<>();
    private Menu menu;

    private void showTitle() {
        System.out.println(
                """
                 █████╗ ██████╗ ██╗ ██████╗██╗ ██████╗ ███╗   ██╗ █████╗ ██████╗      ██████╗ █████╗ ██████╗ ██████╗  █████╗ ██████╗ ██╗ ██████╗
                ██╔══██╗██╔══██╗██║██╔════╝██║██╔═══██╗████╗  ██║██╔══██╗██╔══██╗    ██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔══██╗██║██╔═══██╗
                ███████║██║  ██║██║██║     ██║██║   ██║██╔██╗ ██║███████║██████╔╝    ██║     ███████║██████╔╝██║  ██║███████║██████╔╝██║██║   ██║
                ██╔══██║██║  ██║██║██║     ██║██║   ██║██║╚██╗██║██╔══██║██╔══██╗    ██║     ██╔══██║██╔══██╗██║  ██║██╔══██║██╔═══╝ ██║██║   ██║
                ██║  ██║██████╔╝██║╚██████╗██║╚██████╔╝██║ ╚████║██║  ██║██║  ██║    ╚██████╗██║  ██║██║  ██║██████╔╝██║  ██║██║     ██║╚██████╔╝
                ╚═╝  ╚═╝╚═════╝ ╚═╝ ╚═════╝╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═╝     ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝ ╚═════╝
                """
        );
        
    }

    private void addNewMenu() {
        String name = readString("Digite o nome do cardápio: ");
        String description = readString("Digite a descricao para esse cardápio: ");
        menu = MenuFormCore.createMenu(name, description);
    }
    
    public void addingNewMenu() {
        ConsoleUtils.clear();
        showTitle();
        addNewMenu();
        MenuItemsView menuItemsView = new MenuItemsView(menuItems);
        menuItemsView.editingMenu();
        while (true) {
            int option = readInt("Deseja adicionar este item: 1-SIM | 2-NÃO");
            if (option == 1) {
                MenuFormCore.saveMenu(menu, menuItems);
                return;

                } else if (option == 2) {
                return;
            }
        }

    }
}
