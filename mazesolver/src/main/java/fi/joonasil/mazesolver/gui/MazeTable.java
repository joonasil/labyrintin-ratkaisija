/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import fi.joonasil.mazesolver.util.Data;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Joonas
 */
public class MazeTable {
    
    public static void display(ObservableList<Data> data){
        TableView<Data> table = generateTable(data);
        Stage window = new Stage();
        window.setTitle("Maze");
        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(table,700,600);
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }
    
    private static TableView<Data> generateTable(ObservableList<Data> data){
        TableView<Data> table = new TableView();
        
        //genAlg column
        TableColumn<Data, String> genAlgColumn = new TableColumn("Gen algorithm");
        genAlgColumn.setMinWidth(50);
        genAlgColumn.setCellValueFactory(new PropertyValueFactory<>("genAlg"));
        
        //Size column
        TableColumn<Data, String> sizeColumn = new TableColumn("Size");
        sizeColumn.setMinWidth(50);
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        
        //TimeToGenerate column
        TableColumn<Data, Long> generateColumn = new TableColumn("Time to gen");
        generateColumn.setMinWidth(100);
        generateColumn.setCellValueFactory(new PropertyValueFactory<>("generate"));
        
        //BFS column
        TableColumn<Data, Long> bfsColumn = new TableColumn("BFS");
        bfsColumn.setMinWidth(100);
        bfsColumn.setCellValueFactory(new PropertyValueFactory<>("bfs"));
        
        //AStar column
        TableColumn<Data, Long> aStarColumn = new TableColumn("A-star");
        aStarColumn.setMinWidth(100);
        aStarColumn.setCellValueFactory(new PropertyValueFactory<>("astar"));
        
        //AStar column
        TableColumn<Data, Long> idaColumn = new TableColumn("IDA");
        idaColumn.setMinWidth(100);
        idaColumn.setCellValueFactory(new PropertyValueFactory<>("ida"));
        
        //Button column
        TableColumn<Data, Button> buttonColumn = new TableColumn("Image");
        buttonColumn.setMinWidth(100);
        buttonColumn.setCellValueFactory(new PropertyValueFactory<>("button"));
        
        table.setItems(data);
        table.getColumns().addAll(genAlgColumn,sizeColumn,generateColumn,bfsColumn,aStarColumn,idaColumn,buttonColumn);
        return table;
    }
}
