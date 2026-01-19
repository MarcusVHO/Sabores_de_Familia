package br.com.saboresdefamilia.menu.service;


import br.com.saboresdefamilia.connection.ConnectionFactory;
import br.com.saboresdefamilia.menu.dao.MenuDAO;
import br.com.saboresdefamilia.menu.dao.MenuItemDAO;
import br.com.saboresdefamilia.menu.dto.CompleteMenuDTO;
import br.com.saboresdefamilia.menu.model.Menu;
import br.com.saboresdefamilia.menu.model.MenuItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuService {
    public static void createNewMenu(CompleteMenuDTO completeMenuDTO) {
            Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            MenuDAO.create(completeMenuDTO.getMenu(), conn);

            for(MenuItem menuItem : completeMenuDTO.getMenuItems()) {
                menuItem.setMenuID(completeMenuDTO.getMenu().getId());
                MenuItemDAO.create(menuItem, conn);
            }

            conn.commit();

        } catch (Exception e) {
            try {
                assert conn != null;
                conn.rollback();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.closeConnection(conn);
        }

    }

    public static ArrayList<CompleteMenuDTO> getMenus() {
        Connection conn = null;
        ArrayList<CompleteMenuDTO> completeMenuDTOS = new ArrayList<>();

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            ArrayList<Menu> menus = MenuDAO.selectAllMenus(conn);

            for (Menu menu : menus) {
                ArrayList<MenuItem> menuItems =  MenuItemDAO.selectMenuItems(menu.getId(), conn);
                CompleteMenuDTO completeMenuDTO = new CompleteMenuDTO(menu, menuItems);
                completeMenuDTOS.add(completeMenuDTO);
            }

            return completeMenuDTOS;

        } catch (Exception e) {
            try {
                assert conn != null;
                conn.rollback();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }


    public static void updateMenu(CompleteMenuDTO completeMenuDTO) {
        Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            MenuDAO.updateMenu(completeMenuDTO.getMenu(), conn);

            MenuItemDAO.deleteByMenuID(completeMenuDTO.getMenu().getId(), conn);
            for(MenuItem menuItem : completeMenuDTO.getMenuItems()) {
                menuItem.setMenuID(completeMenuDTO.getMenu().getId());
                MenuItemDAO.create(menuItem, conn);
            }

            conn.commit();

        } catch (Exception e) {
            try {
                assert conn != null;
                conn.rollback();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }

    public static void deleteMenu(Menu menu) {
        Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            MenuItemDAO.deleteByMenuID(menu.getId(), conn);
            MenuDAO.deleteMenu(menu, conn);
            conn.commit();

        } catch (Exception e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.closeConnection(conn);
        }
    }
}
