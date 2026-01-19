package br.com.saboresdefamilia.menu.view;

import br.com.saboresdefamilia.menu.core.MenuCore;
import br.com.saboresdefamilia.menu.model.MenuItem;
import br.com.saboresdefamilia.shared.util.ConsoleUtils;
import br.com.saboresdefamilia.shared.view.BaseView;

import java.util.ArrayList;

public class MenuItemsView extends BaseView {
    private final ArrayList<MenuItem> menuItems;
    private boolean editingList = true;


    public MenuItemsView(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void editingMenu() {
        while (editingList) {
            ConsoleUtils.clear();
            listObjects(menuItems, "Itens do Menu");
            showOptions();
            Integer option = readInt("Digite a opção desejada: ");
            router(option);

        }
    }

    private void showOptions() {
        System.out.println(
                """
                1 - Adicionar Item | 2 - Editar Item | 3 - Remover Item | 4 - Confirmar
                """
        );
    }

    private void router(Integer option) {
        switch (option) {
            case 1:
                addItemInList();
                break;
            case 2:
                editItemInList();
                break;
            case 3:
                removeItemInList();
                break;
            case 4, 0:
                exit();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private void addItemInList() {
        String name = readString("Nome do Item: ");
        String category = readString("Categoria do Item: ");
        menuItems.add(MenuCore.createMenuItem(name, category));
    }

    private void editItemInList() {
        int index = readInt("Digite o index do item que deseja editar: ");
        if (menuItems.size() < index) {
            return;
        }
        MenuItem menuItem = menuItems.get(index);
        while (true) {
            System.out.println(menuItems.get(index));
            System.out.println(
                    """
                    1 - Editar Nome
                    2 - Editar Categoria
                    3 - Confirmar
                    4 - Cancelar
                    """
            );

            int option = readInt("Digite a opção desejada: ");

            switch (option) {
                case 1:
                    String newName = readString("Digite o novo nome: ");
                    menuItem.setName(newName);
                case 2:
                    String newCategory = readString("Digite a nova categoria: ");
                    menuItem.setName(newCategory);
                    break;
                case 3:
                    menuItems.set(index, menuItem);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida!!");
                    break;
            }
        }

    }

    private void removeItemInList() {
        int index = readInt("Digite o index do item que deseja editar: ");
        if (menuItems.size() < index) {
            return;
        }
        MenuItem menuItem = menuItems.get(index);

        while (true) {
            System.out.println(menuItem);
            int option = readInt("Deseja remover este item: 1-SIM | 2-NÃO");

            if (option == 1) {
                menuItems.remove(index);
                return;
            } else if (option == 2) {
                return;
            }
        }
    }

    private void exit() {
        editingList = false;
    }
}
