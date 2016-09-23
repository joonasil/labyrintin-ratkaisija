/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import fi.joonasil.mazesolver.Mazesolver;
import fi.joonasil.mazesolver.logic.generator.Maze;
import fi.joonasil.mazesolver.logic.solver.Solver;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Joonas
 */
public class Screen {
    
    private Scene scene;
    
    public Screen() {
        setScene();
    }
    
    public void setScene() {
        VBox layout = new VBox();
        HBox hbox = new HBox();
        hbox.getChildren().addAll(setInfo(),setImage());
        hbox.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(Menus.setMenuBar(), hbox);
        scene = new Scene(layout, 1240, 720);
    }
    
    public Scene getScene() {
        return this.scene;
    }
       
    public static ScrollPane setImage() {
        Maze maze = Mazesolver.getMaze();
        final int newX = 2*maze.getX()+1;
        final int newY = 2*maze.getY()+1;
        final int sum = newX+newY;
        int multiplier = 4;
        if(sum < 500)
            multiplier = 8;
        if(sum > 2000)
            multiplier = 2;
        ImageView image = maze.getImage();
        image.setFitHeight(newY*multiplier);
        image.setFitWidth(newX*multiplier);
        ScrollPane scroll = createScrollPane(image);
        return scroll;
    }
    
    public static VBox setInfo() {
        VBox info = new VBox();
        Maze maze = Mazesolver.getMaze();
        long start = System.currentTimeMillis();
        Label generate = new Label("Time to generate: " + maze.getTimeToGenerate() + "ms");
        maze.solveBreadthFrist();
        Label bfs = new Label("Time to solve bfs: " + (System.currentTimeMillis()-start) + "ms");
        
        start = System.currentTimeMillis();
        maze.solveAStar();
        Label astar = new Label("Time to solve a*: " + (System.currentTimeMillis()-start) + "ms");
        
        final int newX = 2*maze.getX()+1;
        final int newY = 2*maze.getY()+1;
        Label size = new Label("Size of maze: " + newX + "x" + newY);
        size.setStyle("-fx-font-weight: bold");
        bfs.setStyle("-fx-font-weight: bold");
        astar.setStyle("-fx-font-weight: bold");
        generate.setStyle("-fx-font-weight: bold");
        info.getChildren().add(size);
        info.getChildren().addAll(generate,bfs,astar);
        info.setMinWidth(200);
        info.setSpacing(10);
        info.setPadding(new Insets(0, 10, 0, 10));
        return info;
    }
    
    /**
     * 
     * Metodi luo liikkuvan alustan labyrintin kuvalle, joten koko labyrintin
     * ei tarvitse olla piirrettynä näytölle.
     * 
     * @param maze Labyrintin kuva
     * @return Alustan kuvalle, jota pystyy liikuttamaan hiirellä vetämällä tai nuolinäppäimillä.
     */
    private static ScrollPane createScrollPane(ImageView maze) {
        int x = 1050;
        int y = 680;
        ScrollPane scroll = new ScrollPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setPannable(true);
        if(maze.getFitWidth() < 1200)
            x = (int)maze.getFitWidth()+1;
        if(maze.getFitHeight() < 680)
            y = (int)maze.getFitHeight()+1;
        scroll.setPrefSize(x, y);
        scroll.setContent(maze);
        return scroll;
    }
    
}
