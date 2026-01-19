package br.com.saboresdefamilia.menu.core;

import br.com.saboresdefamilia.menu.model.Menu;
import br.com.saboresdefamilia.menu.service.MenuService;

public class MenuDeleteCore extends MenuCore{

    public static void deleteMenu(Menu menu) {
        MenuService.deleteMenu(menu);
    }
}
