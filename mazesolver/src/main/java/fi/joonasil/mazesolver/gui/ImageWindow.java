/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Luokka piirtää yhden labyrintin kuvan uuteen ikkunaan.
 * @author Joonas
 */
public class ImageWindow {
    
    public static void display(ImageView image, int x, int y) {
        image.setFitHeight(y);
        image.setFitWidth(x);
        ScrollPane pane = createScrollPane(image);
        Stage window = new Stage();
        window.setTitle("Maze" + x + " x " + y);
        Scene scene = new Scene(pane,1240,780);
        window.setResizable(true);
        window.setScene(scene);
        window.show();
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
        if(maze.getFitWidth() < 1000)
            x = (int)maze.getFitWidth()+1;
        if(maze.getFitHeight() < 680)
            y = (int)maze.getFitHeight()+1;
        scroll.setPrefSize(x, y);
        scroll.setContent(maze);
        return scroll;
    }
}
