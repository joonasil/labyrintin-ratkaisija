/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

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
        
        selectMaze.setOnAction(e -> {
//            lol = new Maze(x,y,rand);
        });
//        quitMaze.setOnAction(e -> );

        mazeMenu.getItems().addAll(selectMaze, quitMaze);
        menuBar.getMenus().add(mazeMenu);
        return menuBar;
    }
}
