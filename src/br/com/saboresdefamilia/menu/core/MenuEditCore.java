package br.com.saboresdefamilia.menu.core;

import br.com.saboresdefamilia.menu.dto.CompleteMenuDTO;
import br.com.saboresdefamilia.menu.model.Menu;
import br.com.saboresdefamilia.menu.model.MenuItem;
import br.com.saboresdefamilia.menu.service.MenuService;

import java.util.ArrayList;

public class MenuEditCore extends MenuCore{


    public void updateMenu(Menu menu, ArrayList<MenuItem> menuItems) {
        CompleteMenuDTO completeMenuDTO = new CompleteMenuDTO(menu, menuItems);
        MenuService.updateMenu(completeMenuDTO);
    }

}
