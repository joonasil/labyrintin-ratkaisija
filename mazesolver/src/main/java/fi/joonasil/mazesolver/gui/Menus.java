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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        MenuItem helpMenu = new MenuItem("Help");
        
        selectMaze.setOnAction(e -> {
            CreateMaze.display();
        });
        quitMaze.setOnAction(e -> Main.close());
        
        logMenu.setOnAction(e -> DataLogger.display());
        
        helpMenu.setOnAction(e -> displayHelp());
        
        mazeMenu.getItems().addAll(selectMaze, logMenu, helpMenu, quitMaze);
        menuBar.getMenus().addAll(mazeMenu);
        return menuBar;
    }
    
    private static void displayHelp(){
        Stage window = new Stage();
        window.setTitle("Help");
        VBox layout = new VBox();
        Label info = new Label("Colorcodes: \n\nBlack: Wall.\nWhite: Path visited by no algorithm.\n"
                + "Red: Path visited by breadth-first search only.\nYellow: Path visited by A-star only\n"
                + "Blue: Path visited by iterative deepening A-star only.\nOrange: Path visited by breadth-first search and A-star.\n"
                + "Magneta: Path visited by Breadth-First search and iterative deepening A-star.\n"
                + "Green: Path visited by A-star and iterative deepening A-star.\n"
                + "Brown: Path visited by all three algorithms.\nCyan: Shortest path.");
        info.setStyle("-fx-font-weight: bold");
        layout.getChildren().addAll(info);
        layout.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(layout,600,280);
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }
}
