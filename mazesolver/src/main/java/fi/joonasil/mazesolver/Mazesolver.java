/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver;


import fi.joonasil.mazesolver.gui.ImageConverter;
import fi.joonasil.mazesolver.logic.generator.Maze;
import fi.joonasil.mazesolver.logic.solver.Solver;
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Ohjelman main luokka. Tällä hetkellä vielä vähän sotkuinen kun kaikkea testataan vielä.
 *
 * @author Joonas
 */
public class Mazesolver extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        int x = 200;     /* Muuta x:n arvoa jos haluat eri levyisen labyrintin */
        int y = 200;      /* Muuta y:n arvoa jos haluat eri korkuisen labyrintin */
        int newX = 2*x+1;
        int newY = 2*y+1;
        int sum = newX+newY;
        int multiplier = 4;
        if(sum < 500)
            multiplier = 8;
        if(sum > 2000)
            multiplier = 2;
        
        /*  --Anna konstruktorille joku muuttuja tyyppiä long, 
        jos haluat tietyn kokoisen labyrintin olevan aina sama. */
        Random rand = new Random(1337);
        
        Maze lol = new Maze(x,y,rand);
        
        int[][] newImage = Solver.breadthFirst(lol);
        
        ImageView test = ImageConverter.getImage(newImage,x,y);
        test.setFitHeight(newY*multiplier);
        test.setFitWidth(newX*multiplier);
        
        MenuBar menuBar = new MenuBar();
        Menu mazeMenu = new Menu("Maze");
        MenuItem selectMaze = new MenuItem("Generate Maze");
        MenuItem quitMaze = new MenuItem("Quit Program");
//        selectMaze.setOnAction(e -> );
        quitMaze.setOnAction(e -> primaryStage.close());
        mazeMenu.getItems().addAll(selectMaze, quitMaze);
        menuBar.getMenus().add(mazeMenu);

        VBox layout = new VBox();

        ScrollPane scroll = createScrollPane(test);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(scroll , 1, 1);
        
        layout.getChildren().addAll(menuBar,grid);
        Scene scene = new Scene(layout, 1240, 720);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Maze Solver!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    
    
    /**
     * Käynnistää ohjelman käyttöliittymän.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
    
    /**
     * Metodi tulee muuttamaan toiseen luokkaan joskus!
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
            x = (int)maze.getFitWidth();
        if(maze.getFitHeight() < 680)
            y = (int)maze.getFitHeight();
        scroll.setPrefSize(x, y);
        scroll.setContent(maze);
        return scroll;
     }
    
}
