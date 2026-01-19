package br.com.saboresdefamilia.menu.core;

import br.com.saboresdefamilia.menu.dto.CompleteMenuDTO;
import br.com.saboresdefamilia.menu.model.Menu;
import br.com.saboresdefamilia.menu.model.MenuItem;
import br.com.saboresdefamilia.menu.service.MenuService;

import java.util.ArrayList;

public class MenuCore {
    private ArrayList<CompleteMenuDTO> ResultSearchMenus;

    public void searchMenus() {
        ResultSearchMenus = MenuService.getMenus();
    }

    public static Menu createMenu(String name, String description) {
        Menu menu = new Menu();
        menu.setName(name);
        menu.setDescription(description);
        return menu;
    }

    public static MenuItem createMenuItem(String name, String category) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setCategory(category);
        return menuItem;
    }


    public ArrayList<CompleteMenuDTO> getResultSearchMenus() {
        return ResultSearchMenus;
    }
}
