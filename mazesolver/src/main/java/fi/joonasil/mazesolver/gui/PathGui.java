/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Joonas
 */
public class PathGui {
//    private final int index;
    private Group path;
//    private final Image[] images;
    
    public PathGui(int path, Image[] images){
//        this.index = index;
//        this.images = images;
        ImageView base = new ImageView(images[0]);
        base.setFitHeight(15);
        base.setPreserveRatio(true);
        base.setBlendMode(BlendMode.DARKEN);
//        path = new Group(base);
//        if(map[1] == 0) {
//            ImageView top = new ImageView(images[1]);
//            top.setBlendMode(BlendMode.DARKEN);
//            top.setFitHeight(15);
//            top.setPreserveRatio(true);
//            path.getChildren().add(top);
//        }
//        if(map[3] == 0) {
//            ImageView left = new ImageView(images[4]);
//            left.setBlendMode(BlendMode.DARKEN);
//            left.setFitHeight(15);
//            left.setPreserveRatio(true);
//            path.getChildren().add(left);
//        }
//        if(map[5] == 0) {
//            ImageView right = new ImageView(images[2]);
//            right.setBlendMode(BlendMode.DARKEN);
//            right.setFitHeight(15);
//            right.setPreserveRatio(true);
//            path.getChildren().add(right);
//        }
//        if(map[7] == 0) {
//            ImageView down = new ImageView(images[3]);
//            down.setBlendMode(BlendMode.DARKEN);
//            down.setFitHeight(15);
//            down.setPreserveRatio(true);
//            path.getChildren().add(down);
//        }
    }
    
    /**
     * Lisää toteutus kun a* valmis!
     */
    public void AStarVisited(){
        
    }
    
    public Group getGroup(){
        return this.path;
    }
}
