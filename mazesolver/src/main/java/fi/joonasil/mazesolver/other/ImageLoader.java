/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.other;

import javafx.scene.image.Image;

/**
 *
 * @author Joonas
 */
public class ImageLoader {
    
    private Image[] images = new Image[5];
    
    public Image[] loadImages() {
        images[0] = new Image(getClass().getClassLoader().getResource("gridBase.png").toString());
        images[1] = new Image(getClass().getClassLoader().getResource("WallUp.png").toString());
        images[2] = new Image(getClass().getClassLoader().getResource("WallRight.png").toString());
        images[3] = new Image(getClass().getClassLoader().getResource("WallDown.png").toString());
        images[4] = new Image(getClass().getClassLoader().getResource("WallLeft.png").toString());
        return images;
    }
    
    
}
