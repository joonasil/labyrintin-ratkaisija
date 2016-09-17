/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver;

import fi.joonasil.mazesolver.gui.PathGui;
import fi.joonasil.mazesolver.logic.generator.Maze;
import fi.joonasil.mazesolver.logic.generator.Path;
import fi.joonasil.mazesolver.other.ImageLoader;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Joonas
 */
public class Mazesolver extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        ImageLoader loader = new ImageLoader();
        Image[] images = loader.loadImages();
        int x = 5;     /* 2x+1 */
        int y = 5;      /* 2y+1 */
        int size = (2*x+1)*(2*y+1);
        
        Random rand = new Random(1243567);
        Maze lol = new Maze(x,y,rand);
        
        ImageView test = lol.getImage();
        test.setFitHeight((2*x+1)*4);
        test.setPreserveRatio(true);

   
        Pane pane = new Pane();
        pane.getChildren().add(test);
        
        // wrap the scene contents in a pannable scroll pane.
        ScrollPane scroll = createScrollPane(pane);
        
        Scene scene = new Scene(scroll, 1240, 720);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Maze Solver!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // bind the preferred size of the scroll area to the size of the scene.
        scroll.prefWidthProperty().bind(scene.widthProperty());
        scroll.prefHeightProperty().bind(scene.widthProperty());
        
        // center the scroll contents.
        scroll.setHvalue(scroll.getHmin() + (scroll.getHmax() - scroll.getHmin()) / 2);
        scroll.setVvalue(scroll.getVmin() + (scroll.getVmax() - scroll.getVmin()) / 2);
    }
    
    public int[][] changeDatatype(int x, int y, Path[] maze){
        int[][] uusi = new int[2*x+1][2*y+1];
        int tempx;
        int tempy = 0;
        int[] map;
        for(int i = 1; i < 2*y; i+=2) {
            tempx = 0;
            for(int j = 1; j < 2*x; j+=2) {
                
                map = maze[tempy*x+tempx].getMap();
                
                uusi[j][i] = 1;
                if(map[3] == 1) 
                    uusi[j-1][i] = 1;

                if(map[1] == 1) 
                    uusi[j][i-1] = 1;

                if(map[7] == 1) 
                    uusi[j][i+1] = 1;

                if(map[5] == 1) 
                    uusi[j+1][i] = 1;
                tempx++;
            }
            tempy++;
        }
        return uusi;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public ImageView constructMaze(int x, int y, Image[] images, int[][] path) {
        PathGui[][] paths = new PathGui[x][y];
        WritableImage asd = new WritableImage(x,y);
        PixelWriter writer = asd.getPixelWriter();
        GridPane layout = new GridPane();
        layout.setMaxSize(x * 5, y * 5);
        layout.setMinSize(x * 5, y * 5);
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
//                paths[j][i] = new PathGui(path[j][i] , images);
//                GridPane.setConstraints(paths[i * x + j].getGroup(), j, i);
//                layout.getChildren().add(paths[i * x + j].getGroup());
                if(path[j][i] == 1) {
                    writer.setColor(j, i, Color.WHITE);
                }
                if(path[j][i] == 0) {
                    writer.setColor(j, i, Color.BLACK);
                }
            }
        }
        layout.setPadding(new Insets(0,0,0,0));
        return new ImageView(asd);
    }
    
    private ScrollPane createScrollPane(Pane layout) {
        ScrollPane scroll = new ScrollPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setPannable(true);
        scroll.setPrefSize(1240, 720);
        scroll.setContent(layout);
        return scroll;
     }
    
}
