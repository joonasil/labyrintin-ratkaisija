/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import fi.joonasil.mazesolver.Main;
import fi.joonasil.mazesolver.Mazesolver;
import fi.joonasil.mazesolver.logic.generator.Maze;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Graafisen käyttöliittymän luokka. Luo käyttöliitymän ulkoasun.
 * @author Joonas
 */
public class Screen {
    
    private Scene scene;
    
    public Screen() {
        setScene();
    }
    
    /**
     * Asettaa ikkunaan uuden näkymän.
     */
    public void setScene() {
        VBox layout = new VBox();
        HBox hbox = new HBox();
        hbox.getChildren().addAll(setInfo(),setImage());
        hbox.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(Menus.setMenuBar(), hbox);
        scene = new Scene(layout, 1240, 720);
        Main.setScene(scene);
    }
    
    /**
     * Palauttaa tämänhetkisen näkymän. 
     * @return Näkymä.
     */
    public Scene getScene() {
        return this.scene;
    }
       

    public static ScrollPane setImage() {
        
        Maze maze = Mazesolver.getMaze();
        final int newX = 2*maze.getX()+1;
        final int newY = 2*maze.getY()+1;
        
        ImageView image = maze.getImage();
        image.setFitHeight(newY*4);
        image.setFitWidth(newX*4);
        
        ScrollPane scroll = createScrollPane(image);
        
        DoubleProperty zoomProperty = new SimpleDoubleProperty(newX+newY);

        zoomProperty.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable arg0) {
                image.setFitWidth(zoomProperty.get() * 2);
                image.setFitHeight(zoomProperty.get() * 2);
            }
        });

        scroll.addEventFilter(ScrollEvent.ANY, (e ->  {
            if (e.getDeltaY() > 0) {
                zoomProperty.set(zoomProperty.get() * 1.1);
            } else if (e.getDeltaY() < 0) {
                zoomProperty.set(zoomProperty.get() / 1.1);
            }
        }));
        
        return scroll;
    }
    
    public static VBox setInfo() {
        VBox info = new VBox();
        Maze maze = Mazesolver.getMaze();
        boolean[] solved = maze.getSolved();
        long time;
        
        Label generate = new Label("Time to generate: " + maze.getTimeToGenerate()/1000000 + "ms");
        generate.setStyle("-fx-font-weight: bold");
        
        Label genAlg = new Label("Generation algorithm: " + maze.getGenAlg());
        genAlg.setStyle("-fx-font-weight: bold");
        
        final int newX = 2*maze.getX()+1;
        final int newY = 2*maze.getY()+1;
        Label size = new Label("Size of maze: " + newX + "x" + newY);
        size.setStyle("-fx-font-weight: bold");
        
        Label length = new Label("Shortest path length: " + maze.getPathLength());
        length.setStyle("-fx-font-weight: bold");
        
        info.getChildren().addAll(genAlg,size,generate,length);
        
        if(solved[0]){
            time = maze.getTimeBFS();
            Label bfs = new Label("Time to solve bfs: " + (time/1000000) + "ms");
            bfs.setStyle("-fx-font-weight: bold");
            info.getChildren().add(bfs);
        }else{
            Button solveBFS = new Button("Solve BFS");
            solveBFS.setOnAction(e -> {
                maze.solveBreadthFrist();
                Mazesolver.getScreen().setScene();
            });
            info.getChildren().add(solveBFS);
        }
        
        if(solved[1]){
            time = maze.getTimeAStar();
            Label astar = new Label("Time to solve a*: " + (time/1000000) + "ms");
            astar.setStyle("-fx-font-weight: bold");
            info.getChildren().add(astar);
        }else{
            Button solveAStar = new Button("Solve A-Star");
            solveAStar.setOnAction(e -> {
                Mazesolver.getMaze().solveAStar();
                Mazesolver.getScreen().setScene();
            });
            info.getChildren().add(solveAStar);
        }
       
        if(solved[2]){
            time = maze.getTimeIDA();
            Label ida = new Label("Time to solve ida*: " + (time/1000000) + "ms");
            ida.setStyle("-fx-font-weight: bold");
            info.getChildren().add(ida);
        }else{
            Button solveIDA = new Button("Solve IDA-Star");
            solveIDA.setOnAction(e -> {
                maze.solveIDA();
                Mazesolver.getScreen().setScene();
            });
            info.getChildren().add(solveIDA);
        }
        
        Button save = new Button("Save");
        save.setOnAction(e -> ImageConverter.saveImage(Mazesolver.getMaze().getImage().getImage()));
        
        info.getChildren().add(save);
        info.setMinWidth(250);
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
        int x = 1000;
        int y = 680;
        ScrollPane scroll = new ScrollPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setPannable(true);
        scroll.setPrefSize(x, y);
        scroll.setContent(maze);
        return scroll;
    }
    
}
