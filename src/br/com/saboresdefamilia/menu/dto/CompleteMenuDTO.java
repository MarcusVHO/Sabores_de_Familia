package br.com.saboresdefamilia.menu.dto;

import br.com.saboresdefamilia.menu.model.Menu;
import br.com.saboresdefamilia.menu.model.MenuItem;

import java.util.ArrayList;

public class CompleteMenuDTO {
    private Menu menu;
    private final ArrayList<MenuItem> menuItems;

    public CompleteMenuDTO(Menu menu, ArrayList<MenuItem> menuItems) {
        this.menu = menu;
        this.menuItems = menuItems;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }




    @Override
    public String toString() {
        return getMenu().getName() + " | " + getMenu().getDescription() + " | " + getMenuItems().size() + " Item";
    }
}
