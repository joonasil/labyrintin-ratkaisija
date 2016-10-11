/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.util;

import fi.joonasil.mazesolver.gui.ImageWindow;
import fi.joonasil.mazesolver.logic.generator.Maze;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author Joonas
 */
public class Data {
    private long generate;
    private long bfs;
    private long astar;
    private long ida;
    private String genAlg;
    private String size;
    private Button button;
    private final ImageView image;
    
    public Data(int x, int y, int gen){
        Maze maze = new Maze(x,y, gen);
        maze.solveBreadthFrist();
        maze.solveAStar();
        maze.solveIDA();
        generate = maze.getTimeToGenerate()/1000000;
        bfs = maze.getTimeBFS()/1000000;
        astar = maze.getTimeAStar()/1000000;
        ida = maze.getTimeIDA()/1000000;
        genAlg = maze.getGenAlg();
        size = x + " x " + y;
        button = new Button("image");
        button.setOnAction(e -> buttonPressed(x,y));
        image = maze.getImage();
    }
    
    public Data(long generate, long bfs, long aStar, long ida, String s, String a){
        this.generate = generate;
        this.bfs = bfs;
        this.astar = aStar;
        this.ida = ida;
        genAlg = s;
        size = a;
        button = new Button();
        image = null;
    }
    
    private void buttonPressed(int x, int y){
        ImageWindow.display(image, x , y);
    }

    public long getGenerate() {
        return generate;
    }

    public long getIda() {
        return ida;
    }

    public void setIda(long ida) {
        this.ida = ida;
    }

    public void setGenerate(long generate) {
        this.generate = generate;
    }

    public long getBfs() {
        return bfs;
    }

    public void setBfs(long bfs) {
        this.bfs = bfs;
    }

    public long getAstar() {
        return astar;
    }

    public void setAstar(long astar) {
        this.astar = astar;
    }

    public String getGenAlg() {
        return genAlg;
    }

    public void setGenAlg(String genAlg) {
        this.genAlg = genAlg;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
    
}
