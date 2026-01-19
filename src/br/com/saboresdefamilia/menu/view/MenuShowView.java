package br.com.saboresdefamilia.menu.view;

import br.com.saboresdefamilia.menu.core.MenuCore;
import br.com.saboresdefamilia.shared.util.ConsoleUtils;
import br.com.saboresdefamilia.shared.view.BaseView;

public class MenuShowView extends BaseView {
    private final MenuCore menuCore = new MenuCore();

    private void showTitle() {
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

    private void showMenu(Integer index) {
        System.out.println("||  " + menuCore.getResultSearchMenus().get(index).getMenu() + "  ||");
        listObjects(menuCore.getResultSearchMenus().get(index).getMenuItems(), "Items");
        readString("Digite qualquer coisa para voltar a pesquisa: ");
    }

    public void showingMenus() {
        while (true) {
            menuCore.searchMenus();
            ConsoleUtils.clear();
            showTitle();
            listObjects(menuCore.getResultSearchMenus(), "Cardapios");

            Integer index = readInt("Digite o index que deseja visualizar (deixe em branco para voltar): ");
            if (index == null) {
                return;
            }
            ConsoleUtils.clear();
            showTitle();
            showMenu(index);
        }
    }
}
