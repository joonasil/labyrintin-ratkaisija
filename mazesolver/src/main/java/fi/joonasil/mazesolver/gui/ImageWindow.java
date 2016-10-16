/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

/**
 * Luokka piirtää yhden labyrintin kuvan uuteen ikkunaan.
 * @author Joonas
 */
public class ImageWindow {
    
    public static void display(ImageView image, int x, int y) {
        image.setFitHeight(y*2);
        image.setFitWidth(x*2);
        ScrollPane pane = createScrollPane(image);
        
        DoubleProperty zoomProperty = new SimpleDoubleProperty(x+y);

        zoomProperty.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable arg0) {
                image.setFitWidth(zoomProperty.get() * 1);
                image.setFitHeight(zoomProperty.get() * 1);
            }
        });

        pane.addEventFilter(ScrollEvent.ANY, (e ->  {
            if (e.getDeltaY() > 0) {
                zoomProperty.set(zoomProperty.get() * 1.1);
            } else if (e.getDeltaY() < 0) {
                zoomProperty.set(zoomProperty.get() / 1.1);
            }
        }));
        
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
        scroll.setPrefSize(x, y);
        scroll.setContent(maze);
        return scroll;
    }
}
