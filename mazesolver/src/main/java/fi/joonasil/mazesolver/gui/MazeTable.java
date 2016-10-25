/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.gui;

import fi.joonasil.mazesolver.util.Data;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Joonas
 */
public class MazeTable {
    
    public static void display(ObservableList<Data> data){
        TableView<Data> table = generateTable(data);
        VBox layout = new VBox();
        HBox buttons = new HBox();
        buttons.setMaxHeight(20);
        table.setMinHeight(580);
        Button export = new Button("Export to exel");
        export.setOnAction(e -> {
            try {
                export(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttons.getChildren().add(export);
        layout.getChildren().addAll(table,buttons);
        Stage window = new Stage();
        window.setTitle("Maze");
        window.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(layout,800,600);
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }
    
    private static void export(ObservableList<Data> data) throws Exception {
        Writer writer = null;
        try {
            new File(System.getProperty("user.dir")+"/exel").mkdir();
            File file = new File(System.getProperty("user.dir") + "/exel/" + System.nanoTime() + ".csv");
            writer = new BufferedWriter(new FileWriter(file));
            int i = 1;
            String text;
            for (Data maze : data) {
                if(maze.getGenAlg().equals("Average"))
                    text = "Average" + ";" + maze.getGenerate() + ";" + maze.getBfs() + ";" + maze.getAstar() + ";" + maze.getIda() + ";" + maze.getPathLength() + "\n";
                else if(maze.getGenAlg().equals("Range"))
                    text = "Range" + ";" + maze.getGenerate() + ";" + maze.getBfs() + ";" + maze.getAstar() + ";" + maze.getIda() + ";" + maze.getPathLength() + "\n";
                else
                    text = i + ";" + maze.getGenerate() + ";" + maze.getBfs() + ";" + maze.getAstar() + ";" + maze.getIda() + ";" + maze.getPathLength() + "\n";
                writer.write(text);
                i++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            writer.flush();
            writer.close();
        } 
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
        
        //PathLength column
        TableColumn<Data, Integer> plColumn = new TableColumn("Path Length");
        plColumn.setMinWidth(100);
        plColumn.setCellValueFactory(new PropertyValueFactory<>("pathLength"));
        
        table.setItems(data);
        table.getColumns().addAll(genAlgColumn,sizeColumn,generateColumn,bfsColumn,aStarColumn,idaColumn,plColumn,buttonColumn);
        return table;
    }
}
