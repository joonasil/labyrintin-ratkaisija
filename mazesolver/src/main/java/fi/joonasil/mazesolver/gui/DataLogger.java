/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import fi.joonasil.mazesolver.util.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
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
public class DataLogger {
    
    private static Stage window;
    private static Label widthError;
    private static Label heightError;
    private static Label quantityError;
    /**
     * 
     */
    public static void display() {
        widthError = new Label();
        heightError = new Label();
        quantityError = new Label();
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Log data");
        window.setMinHeight(200);
        window.setMinWidth(200);
        VBox layout = new VBox();
        Label info = new Label("Note: \nDue to how the generation algorithms work "
                + "the resulting maze\nwill have twice the specified amount of nodes "
                + "in both directions.\nThat is why the size of the maze displayed "
                + "differs from the values given here.\n\n"
                + "Please note that generating many large mazes will take a long time.");
        info.setStyle("-fx-font-weight: bold");
        layout.getChildren().addAll(info,setLayout());
        layout.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(layout,600,420);
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
        
        Label quantity = new Label("Mazes:");
        quantity.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(quantity, 0, 3);
        
        TextField quantityInput = new TextField();
        quantityInput.setPromptText("Number of mazes to generate");
        GridPane.setConstraints(quantityInput, 1, 3);
        
        Label gen = new Label("Generation algorithm:");
        gen.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(gen, 0, 4);
        
        ChoiceBox<String> genAlg = new ChoiceBox<>(FXCollections.observableArrayList("Prim's","Depth-first search","Kruskal's"));
        genAlg.getSelectionModel().selectFirst();
        GridPane.setConstraints(genAlg, 1, 4);
        
        Label seed = new Label("Seeded:");
        seed.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(seed, 0, 5);
        
        CheckBox seeded = new CheckBox();
        seeded.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(seeded, 1, 5);
        
        Button close = new Button("Close");
        GridPane.setConstraints(close, 0, 6);
        GridPane.setMargin(close, new Insets(40,0,0,0));
        close.setOnAction(e -> window.close());
        
        Button create = new Button("Log Data");
        GridPane.setConstraints(create, 1, 6);
        GridPane.setMargin(create, new Insets(40,0,0,0));
        create.setOnAction(e -> validateInput(layout, widthInput, heightInput, quantityInput, genAlg, seeded));
        
        layout.getChildren().addAll(width,widthInput,height,heightInput,quantity,quantityInput,gen,genAlg,seed,seeded,close,create);
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
    private static void validateInput(GridPane grid, TextField width, TextField height, TextField quantity, ChoiceBox<String> genAlg, CheckBox seeded) {
        
        width.setStyle("-fx-background-color: #FFFFFF;");
        height.setStyle("-fx-background-color: #FFFFFF;");
        if(grid.getChildren().contains(widthError))
            grid.getChildren().remove(widthError);
        if(grid.getChildren().contains(heightError))
            grid.getChildren().remove(heightError);
        if(grid.getChildren().contains(quantityError))
            grid.getChildren().remove(quantityError);
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
        if(!isInt(quantity)) {
            quantityError.setText("Quantity is not an integer!");
            GridPane.setConstraints(quantityError, 2, 3);
            grid.getChildren().add(quantityError);
            return;
        }
        int x = Integer.parseInt(width.getText());
        int y = Integer.parseInt(height.getText());
        int q = Integer.parseInt(quantity.getText());
        if(x < 2) {
            width.setStyle("-fx-background-color: #FF0000;");
            widthError.setText("Length must be at least 2!");
            GridPane.setConstraints(widthError, 2, 1);
            grid.getChildren().add(widthError);
            return;
        }
        if(y < 2) {
            height.setStyle("-fx-background-color: #FF0000;");
            heightError.setText("Height must be at least 2!");
            GridPane.setConstraints(heightError, 2, 2);
            grid.getChildren().add(heightError);
            return;
        }
        if(q < 1) {
            quantity.setStyle("-fx-background-color: #FF0000;");
            quantityError.setText("Quantity must be at least 1!");
            GridPane.setConstraints(quantityError, 2, 4);
            grid.getChildren().add(quantityError);
            return;
        }
        generateData(x, y, q, genAlg, seeded);
    }
    
    private static void generateData(int x, int y, int q, ChoiceBox<String> genAlg, CheckBox seeded){
        window.close();
        ObservableList<Data> data = FXCollections.observableArrayList();
        long genAvg, bfsAvg, aStarAvg, gMax, bMax, aMax, gMin, bMin, aMin, idaAvg, idaMin, idaMax;
        int pathAvg, pathMin, pathMax;
        Data d = null;
        genAvg = bfsAvg = aStarAvg = idaAvg = gMax = bMax = aMax = idaMax = pathAvg = pathMax = 0;
        gMin = bMin = aMin = idaMin = pathMin = Integer.MAX_VALUE;
        for(int i = 0; i < q; i++){
            if(seeded.isSelected())
                d = new Data(x,y,genAlg.getSelectionModel().getSelectedIndex(),i);
            else
                d = new Data(x,y,genAlg.getSelectionModel().getSelectedIndex());
            if(d.getGenerate() > gMax)
                gMax = d.getGenerate();
            if(d.getGenerate() < gMin)
                gMin = d.getGenerate();
            if(d.getBfs() > bMax)
                bMax = d.getBfs();
            if(d.getBfs() < bMin)
                bMin = d.getBfs();
            if(d.getAstar() > aMax)
                aMax = d.getAstar();
            if(d.getAstar() < aMin)
                aMin = d.getAstar();
            if(d.getIda() > idaMax)
                idaMax = d.getIda();
            if(d.getIda() < idaMin)
                idaMin = d.getIda();
            if(d.getPathLength() > pathMax)
                pathMax = d.getPathLength();
            if(d.getPathLength() < pathMin)
                pathMin = d.getPathLength();
            genAvg += d.getGenerate();
            bfsAvg += d.getBfs();
            aStarAvg += d.getAstar();
            idaAvg += d.getIda();
            pathAvg += d.getPathLength();
            data.add(d);
        }
        data.add(new Data(genAvg/q,bfsAvg/q,aStarAvg/q,idaAvg/q,"Average","times",pathAvg/q));
        data.add(new Data(gMax-gMin,bMax-bMin,aMax-aMin,idaMax-idaMin,"Range","",pathMax-pathMin));
        
        MazeTable.display(data);
    }
}
