/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import fi.joonasil.mazesolver.Main;

/**
 *
 * @author Joonas
 */
public class Menus {
    
    
    public static MenuBar setMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu mazeMenu = new Menu("Maze");
        
        MenuItem selectMaze = new MenuItem("Generate Maze");
        MenuItem quitMaze = new MenuItem("Quit Program");
        MenuItem logMenu = new MenuItem("Data logger");
        
        selectMaze.setOnAction(e -> {
            CreateMaze.display();
        });
        quitMaze.setOnAction(e -> Main.close());
        
        logMenu.setOnAction(e -> DataLogger.display());
        
        mazeMenu.getItems().addAll(selectMaze, logMenu, quitMaze);
        menuBar.getMenus().addAll(mazeMenu);
        return menuBar;
    }
}
