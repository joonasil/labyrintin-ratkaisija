
package fi.joonasil.mazesolver.gui;

import fi.joonasil.mazesolver.Main;
import fi.joonasil.mazesolver.Mazesolver;
import fi.joonasil.mazesolver.logic.generator.Maze;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Luokka luo ikkunan uuden labyrintin luomista varten.
 * @author Joonas
 */
public class CreateMaze {
    
    private static Stage window;
    private static Label widthError;
    private static Label heightError;
    private static Label seedError;
    
    /**
     * Luo ikkunan uuden labyrintin luomista varten.
     */
    public static void display() {
        widthError = new Label();
        heightError = new Label();
        seedError = new Label();
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New maze");
        window.setMinHeight(200);
        window.setMinWidth(200);
        VBox layout = new VBox();
        Label info = new Label("Note: \nLength and height of the generated maze will be \n"
                + "2 times the size specified. I.e. if the specified space is 100x100 \n"
                + "the maze generated will be 200x200 and so will be 4 times larger than specified.");
        info.setStyle("-fx-font-weight: bold");
        layout.getChildren().addAll(info,setLayout());
        layout.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(layout,600,300);
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }
    
    /**
     * Palauttaa totuusarvon siitä, onko annettu syöte kokonaisluku.
     * @param input Käyttäjän syöte.
     * @return Totuusarvo siitä, onko syöte kokonaisluku.
     */
    private static boolean isInt(TextField input) {
        try{
            Integer.parseInt(input.getText());
            return true;
        }catch(NumberFormatException e){
            input.setStyle("-fx-background-color: #FF0000;");
            return false;
        }
    }
    
    /**
     * Asettaa ikkunan ulkoasun.
     * @return Ikkunan ulkoasu.
     */
    private static GridPane setLayout() {
        GridPane layout = new GridPane();
         
        Label width = new Label("Width:");
        width.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(width, 0, 1);
        
        TextField widthInput = new TextField();
        GridPane.setConstraints(widthInput, 1, 1);
        
        Label height = new Label("Height:");
        height.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(height, 0, 2);
        
        TextField heightInput = new TextField();
        GridPane.setConstraints(heightInput, 1, 2);
        
        Label seed = new Label("Seed:");
        seed.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(seed, 0, 3);
        
        TextField seedInput = new TextField();
        seedInput.setPromptText("Leave plank for random.");
        GridPane.setConstraints(seedInput, 1, 3);
        
        Button close = new Button("Close");
        GridPane.setConstraints(close, 0, 4);
        close.setOnAction(e -> window.close());
        
        Button create = new Button("Generate");
        GridPane.setConstraints(create, 1, 4);
        create.setOnAction(e -> validateInput(layout, widthInput, heightInput, seedInput));
        
        layout.getChildren().addAll(width,widthInput,height,heightInput,seed,seedInput,close,create);
        layout.setVgap(8);
        layout.setHgap(10);
        layout.setPadding(new Insets(10,10,10,10));
        return layout;
    }
     
    /**
     * Tarkistaa annetut syötteet.
     * @param grid Ikkunan ulkoasu.
     * @param width Annettu syöte labyrintin leveydeksi.
     * @param height Annettu syöte labyrintin korkeudeksi.
     * @param seed Annettu syöte labyrintin generoinnin seediksi.
     */
    private static void validateInput(GridPane grid, TextField width, TextField height, TextField seed) {
        
        width.setStyle("-fx-background-color: #FFFFFF;");
        height.setStyle("-fx-background-color: #FFFFFF;");
        if(grid.getChildren().contains(widthError))
            grid.getChildren().remove(widthError);
        if(grid.getChildren().contains(heightError))
            grid.getChildren().remove(heightError);
        if(grid.getChildren().contains(seedError))
            grid.getChildren().remove(seedError);
        if(!isInt(width)) {
            widthError.setText("Width is not an integer!");
            GridPane.setConstraints(widthError, 2, 1);
            grid.getChildren().add(widthError);
            return;
        }
        if(!isInt(height)) {
            heightError.setText("Height is not an integer!");
            GridPane.setConstraints(heightError, 2, 2);
            grid.getChildren().add(heightError);
            return;
        }
        if(!seed.getText().isEmpty() && !isInt(seed)) {
            seedError.setText("Seed is not an integer!");
            GridPane.setConstraints(seedError, 2, 3);
            grid.getChildren().add(seedError);
            return;
        }
        int x = Integer.parseInt(width.getText());
        int y = Integer.parseInt(height.getText());
        if(x < 2) {
            width.setStyle("-fx-background-color: #FF0000;");
            widthError.setText("Length must be at least 2!");
            GridPane.setConstraints(widthError, 2, 1);
            grid.getChildren().add(widthError);
            return;
        }
        if(y < 2) {
            height.setStyle("-fx-background-color: #FF0000;");
            heightError.setText("Height must be at lest 2!");
            GridPane.setConstraints(heightError, 2, 2);
            grid.getChildren().add(heightError);
            return;
        }
        if(isInt(width) && isInt(height) && seed.getText().isEmpty()) {
            Mazesolver.setMaze(new Maze(x,y));
            Mazesolver.getScreen().setScene();
            Main.setScene(Mazesolver.getScreen().getScene());
            window.close();
        }
        if(isInt(width) && isInt(height) && !seed.getText().isEmpty()) {
            Mazesolver.setMaze(new Maze(x,y,Integer.parseInt(seed.getText())));
            Mazesolver.getScreen().setScene();
            Main.setScene(Mazesolver.getScreen().getScene());
            window.close();
        }
    }
    
}
