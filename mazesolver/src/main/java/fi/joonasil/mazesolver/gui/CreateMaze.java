/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import fi.joonasil.mazesolver.Main;
import fi.joonasil.mazesolver.Mazesolver;
import fi.joonasil.mazesolver.logic.generator.Maze;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
 *
 * @author Joonas
 */
public class CreateMaze {
    private static Stage window;
    private static Label lengthError;
    private static Label heightError;
    private static Label seedError;
    public static void display() {
        lengthError = new Label();
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
    
    private static boolean isInt(TextField input) {
        try{
            Integer.parseInt(input.getText());
            return true;
        }catch(NumberFormatException e){
            input.setStyle("-fx-background-color: #FF0000;");
            return false;
        }
    }
    
    private static GridPane setLayout() {
        GridPane layout = new GridPane();
         
        Label length = new Label("Length:");
        length.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(length, 0, 1);
        
        TextField lengthInput = new TextField();
        GridPane.setConstraints(lengthInput, 1, 1);
        
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
        create.setOnAction(e -> validateInput(layout, lengthInput, heightInput, seedInput));
        
        layout.getChildren().addAll(length,lengthInput,height,heightInput,seed,seedInput,close,create);
        layout.setVgap(8);
        layout.setHgap(10);
        layout.setPadding(new Insets(10,10,10,10));
        return layout;
    }
     
    private static void validateInput(GridPane grid, TextField length, TextField height, TextField seed) {
        
        length.setStyle("-fx-background-color: #FFFFFF;");
        height.setStyle("-fx-background-color: #FFFFFF;");
        if(grid.getChildren().contains(lengthError))
            grid.getChildren().remove(lengthError);
        if(grid.getChildren().contains(heightError))
            grid.getChildren().remove(heightError);
        if(grid.getChildren().contains(seedError))
            grid.getChildren().remove(seedError);
        if(!isInt(length)) {
            lengthError.setText("Length is not an integer!");
            GridPane.setConstraints(lengthError, 2, 1);
            grid.getChildren().add(lengthError);
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
        int x = Integer.parseInt(length.getText());
        int y = Integer.parseInt(height.getText());
        if(x < 2) {
            length.setStyle("-fx-background-color: #FF0000;");
            lengthError.setText("Length must be at least 2!");
            GridPane.setConstraints(lengthError, 2, 1);
            grid.getChildren().add(lengthError);
            return;
        }
        if(y < 2) {
            height.setStyle("-fx-background-color: #FF0000;");
            heightError.setText("Height must be at lest 2!");
            GridPane.setConstraints(heightError, 2, 2);
            grid.getChildren().add(heightError);
            return;
        }
        if(isInt(length) && isInt(height) && seed.getText().isEmpty()) {
            Mazesolver.setMaze(new Maze(x,y));
            Mazesolver.getScreen().setScene();
            Main.setScene(Mazesolver.getScreen().getScene());
            window.close();
        }
        if(isInt(length) && isInt(height) && !seed.getText().isEmpty()) {
            Mazesolver.setMaze(new Maze(x,y,Integer.parseInt(seed.getText())));
            Mazesolver.getScreen().setScene();
            Main.setScene(Mazesolver.getScreen().getScene());
            window.close();
        }
    }
    
}
