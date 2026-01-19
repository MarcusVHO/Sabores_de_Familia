package br.com.saboresdefamilia.menu.core;

import br.com.saboresdefamilia.menu.dto.CompleteMenuDTO;
import br.com.saboresdefamilia.menu.model.Menu;
import br.com.saboresdefamilia.menu.model.MenuItem;
import br.com.saboresdefamilia.menu.service.MenuService;

import java.util.ArrayList;


public class MenuFormCore extends MenuCore {

    public static void saveMenu(Menu menu, ArrayList<MenuItem> menuItems) {
        CompleteMenuDTO completeMenuDTO = new CompleteMenuDTO(menu, menuItems);
        MenuService.createNewMenu(completeMenuDTO);
    }

}
