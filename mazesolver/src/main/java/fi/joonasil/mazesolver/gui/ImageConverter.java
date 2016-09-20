/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Luokka labyrintin esittämiseksi graafisesti.
 * @author Joonas
 */
public class ImageConverter {
    
    /**
     * Metodi luo labyrintistä kuvan kaksiuloitteisen kokonaislukutaulukon pohjalta.
     * 
     * @param base Labyrintti esitettynä kaksiuloitteisena kokonaislukutaulukkona.
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     * @return ImageView-tyyppinen kuva labyrintista.
     */
    public static ImageView getImage(int[][] base,int x, int y) {
        
        WritableImage output = new WritableImage(2*x+1,2*y+1);
        PixelWriter writer = output.getPixelWriter();
        for (int i = 0; i < 2*y+1; i++) {
            for (int j = 0; j < 2*x+1; j++) {
                if(base[j][i] == 1) {
                    writer.setColor(j, i, Color.WHITE);
                }
                if(base[j][i] == 0) {
                    writer.setColor(j, i, Color.BLACK);
                }
                if(base[j][i] == 3) {
                    writer.setColor(j, i, Color.RED);
                }
                if(base[j][i] == 11) {
                    writer.setColor(j, i, Color.AQUA);
                }
                if(base[j][i] == 4) {
                    writer.setColor(j, i, Color.GREEN);
                }
                if(base[j][i] == 6) {
                    writer.setColor(j, i, Color.ORANGE);
                }
            }
        }
        return new ImageView(output);
    }
}
