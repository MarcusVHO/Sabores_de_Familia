package br.com.saboresdefamilia.menu.view;

import br.com.saboresdefamilia.menu.core.MenuDeleteCore;
import br.com.saboresdefamilia.shared.util.ConsoleUtils;
import br.com.saboresdefamilia.shared.view.BaseView;

public class MenuDeleteView extends BaseView {
    private final MenuDeleteCore menuDeleteCore = new MenuDeleteCore();

    private void showTitle() {
        System.out.println(
                """
                ██████╗ ███████╗██╗     ███████╗████████╗ █████╗ ██████╗\s
                ██╔══██╗██╔════╝██║     ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗
                ██║  ██║█████╗  ██║     █████╗     ██║   ███████║██████╔╝
                ██║  ██║██╔══╝  ██║     ██╔══╝     ██║   ██╔══██║██╔══██╗
                ██████╔╝███████╗███████╗███████╗   ██║   ██║  ██║██║  ██║
                ╚═════╝ ╚══════╝╚══════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝
                """
        );
    }

    private Integer selectMenu() {
        listObjects(menuDeleteCore.getResultSearchMenus(), "Cardapios");
        int index = readInt("Digite o index do item que deseja deletar: ");
        if (index+1 > menuDeleteCore.getResultSearchMenus().size()) {
            return null;
        }
        return index;
    }

    private boolean check() {
        while (true) {
            int number = readInt("Deseja deletar este cardapio? 1-SIM | 2-NÃO ");
            if (number == 1) {
                return true;
            } else if (number == 2) {
                return false;
            }
        }
    }

    public void deletingMenu() {
        menuDeleteCore.searchMenus();
        ConsoleUtils.clear();
        showTitle();

        Integer index = selectMenu();
        if (index == null) {
            return;
        }

        if(check()) {
            MenuDeleteCore.deleteMenu(menuDeleteCore.getResultSearchMenus().get(index).getMenu());
        }
    }
}
