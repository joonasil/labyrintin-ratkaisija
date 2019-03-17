/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;

/**
 * Luokka labyrintin esittämiseksi graafisesti.
 * @author Joonas
 */
public class ImageConverter {
    
    /**
     * Metodi luo labyrintistä kuvan kaksiuloitteisen kokonaislukutaulukon pohjalta.
     * 
     * @param base Labyrintti esitettynä kaksiuloitteisena kokonaislukutaulukkona.
     * @return ImageView-tyyppinen kuva labyrintista.
     */
    public static ImageView getImage(int[][] base) {
        WritableImage output = new WritableImage(base.length,base[0].length);
        PixelWriter writer = output.getPixelWriter();
        for (int i = 0; i < base[0].length; i++) {
            for (int j = 0; j < base.length; j++) {
                if(base[j][i] == 0) {
                    writer.setColor(j, i, Color.BLACK);
                }
                if(base[j][i] == 1) {
                    writer.setColor(j, i, Color.WHITE);
                }
                if(base[j][i] == 3) {
                    writer.setColor(j, i, Color.RED);
                }
                if(base[j][i] == 4) {
                    writer.setColor(j, i, Color.GREEN);
                }
                if(base[j][i] == 5) {
                    writer.setColor(j, i, Color.BLUE);
                }
                if(base[j][i] == 6) {
                    writer.setColor(j, i, Color.ORANGE);
                }
                if(base[j][i] == 7) {
                    writer.setColor(j, i, Color.MAGENTA);
                }
                if(base[j][i] == 8) {
                    writer.setColor(j, i, Color.YELLOW);
                }
                if(base[j][i] == 10) {
                    writer.setColor(j, i, Color.BROWN);
                }
                if(base[j][i] == 11) {
                    writer.setColor(j, i, Color.AQUA);
                }
            }
        }
        return new ImageView(output);
    }
    
    /**
     * Tallentaa parametrina annetun kuvan .png tiedostona images kansioon.
     * @param image Tallennettava kuva.
     */
    public static void saveImage(Image image){
        new File(System.getProperty("user.dir")+"/images").mkdir();
        Path path = Paths.get(System.getProperty("user.dir") + "/images/" + System.nanoTime() + ".png");
        File outputFile = new File(path.toUri());
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
          ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
    }
}
