/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import fi.joonasil.mazesolver.logic.generator.Maze;
import fi.joonasil.mazesolver.logic.solver.Solver;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Joonas
 */
public class Screen {
    
    private final Scene scene;
    
    public Screen() {
        
        final int x = 1000;     /* Muuta x:n arvoa jos haluat eri levyisen labyrintin */
        final int y = 1000;      /* Muuta y:n arvoa jos haluat eri korkuisen labyrintin */
        final int newX = 2*x+1;
        final int newY = 2*y+1;
        final int sum = newX+newY;
        int multiplier = 4;
        if(sum < 500)
            multiplier = 8;
        if(sum > 2000)
            multiplier = 2;
        
        
        
        /*  --Anna konstruktorille joku kokonaisluku, 
        jos haluat tietyn kokoisen labyrintin olevan aina sama. */
        final Random rand = new Random(1234567890);
        
        Maze lol = new Maze(x,y,rand);
        long start = System.currentTimeMillis();
        int[][] newImage = Solver.breadthFirst(lol);
        System.out.println("Time to solve bfs: " + (System.currentTimeMillis()-start));
        lol.setNewMaze(newImage);
        
        start = System.currentTimeMillis();
        newImage = Solver.aStar(lol);
        System.out.println("Time to solve a*: " + (System.currentTimeMillis()-start));
        
        ImageView test = ImageConverter.getImage(newImage,x,y);
        test.setFitHeight(newY*multiplier);
        test.setFitWidth(newX*multiplier);
        
        
        
        
        VBox layout = new VBox();
        GridPane grid = new GridPane();
        ScrollPane scroll = createScrollPane(test);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(scroll , 1, 1);
        layout.getChildren().addAll(Menus.setMenuBar(), grid);
        scene = new Scene(layout, 1240, 720);
    }
    
    
    public Scene getScene() {
        return this.scene;
    }
    
  
    /**
     * 
     * Metodi luo liikkuvan alustan labyrintin kuvalle, joten koko labyrintin
     * ei tarvitse olla piirrettynä näytölle.
     * 
     * @param maze Labyrintin kuva
     * @return Alustan kuvalle, jota pystyy liikuttamaan hiirellä vetämällä tai nuolinäppäimillä.
     */
    private ScrollPane createScrollPane(ImageView maze) {
        int x = 1200;
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
