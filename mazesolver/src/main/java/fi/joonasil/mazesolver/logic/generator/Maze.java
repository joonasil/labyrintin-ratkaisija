/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

import fi.joonasil.mazesolver.gui.ImageConverter;
import fi.joonasil.mazesolver.logic.solver.Solver;
import fi.joonasil.mazesolver.util.Generator;
import java.util.Random;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
/**
 * Luokka labyrintille.
 * @author Joonas
 */
public class Maze {
    
    private final long timeToGenerate;
    private final boolean[] solved;
    private final long[] timeToSolve;
    private ImageView image;
    private int[][] maze;
    private final int x;
    private final int y;
    private final String genAlg;
    /**
     * 
     * Konstruktori luo uuden labyrintin käyttäen randomisoitua primin algoritmia tällä hetkellä.
     * 
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     */
    public Maze(int x, int y) {
        Random rand = new Random();
        this.x = x;
        this.y = y;
        long start = System.nanoTime();
        maze = Generator.generatePrim(rand, x, y);
        genAlg = "Prim's";
        timeToGenerate = System.nanoTime()-start;
        image = ImageConverter.getImage(maze);
        solved = new boolean[3];
        timeToSolve = new long[3];
    }
    
    public Maze(int x, int y, ChoiceBox<String> genAlg) {
        Random rand = new Random();
        this.x = x;
        this.y = y;
        long start = System.nanoTime();
        switch (genAlg.getSelectionModel().getSelectedIndex()) {
            case 0:
                maze = Generator.generatePrim(rand, x, y);
                this.genAlg = "Prim's";
                break;
            case 1:
                maze = Generator.generateDFS(rand, x, y);
                this.genAlg = "Depth-first search";
                break;
            case 2:
                maze = Generator.generateKruskal(rand, x, y);
                this.genAlg = "Kruskal's";
                break;
            default:
                this.genAlg = "Error";
                System.out.println("Error!");
        }
        timeToGenerate = System.nanoTime()-start;
        image = ImageConverter.getImage(maze);
        solved = new boolean[3];
        timeToSolve = new long[3];
    }
    
    /**
     * Konstruktori luo uuden labyrintin käyttäen randomisoitua primin algoritmiä, jolle on annettu tietty seedi.
     * 
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     * @param seed Seedi labyrintin luomista varten.
     */
    public Maze(int x, int y, long seed) {
        Random rand = new Random(seed);
        this.x = x;
        this.y = y;
        this.genAlg = "Prim's";
        long start = System.nanoTime();
        maze = Generator.generatePrim(rand,x,y);
        timeToGenerate = System.nanoTime()-start;
        image = ImageConverter.getImage(maze);
        solved = new boolean[3];
        timeToSolve = new long[3];
    }
    
    /**
     * Konstruktori luo uuden labyrintin käyttäen randomisoitua primin algoritmiä, jolle on annettu tietty seedi.
     * 
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     * @param seed Seedi labyrintin luomista varten.
     * @param genAlg Mitä generointialgoritmia käytetään.
     */
    public Maze(int x, int y, long seed, ChoiceBox<String> genAlg) {
        Random rand = new Random(seed);
        this.x = x;
        this.y = y;
        long start = System.nanoTime();
        switch (genAlg.getSelectionModel().getSelectedIndex()) {
            case 0:
                maze = Generator.generatePrim(rand, x, y);
                this.genAlg = "Prim's";
                break;
            case 1:
                maze = Generator.generateDFS(rand, x, y);
                this.genAlg = "Depth-first search";
                break;
            case 2:
                maze = Generator.generateKruskal(rand, x, y);
                this.genAlg = "Kruskal's";
                break;
            default:
                this.genAlg = "Error";
                System.out.println("Error!");
        }
        timeToGenerate = System.nanoTime()-start;
        image = ImageConverter.getImage(maze);
        solved = new boolean[3];
        timeToSolve = new long[3];
        
    }
    
    public void solveBreadthFrist() {
        long start = System.nanoTime();
        Solver.breadthFirst(maze);
        long end = System.nanoTime()-start;
        this.image = ImageConverter.getImage(maze);
        timeToSolve[0] = end;
        solved[0] = true;
    }
    
    public void solveAStar() {
        long start = System.nanoTime();
        Solver.aStar(maze);
        long end = System.nanoTime()-start;
        this.image = ImageConverter.getImage(maze);
        timeToSolve[1] = end;
        solved[1] = true;
    }
    
    public void solveIDA() {
        long start = System.nanoTime();
        Solver.IDA(maze);
        long end = System.nanoTime()-start;
        this.image = ImageConverter.getImage(maze);
        timeToSolve[2] = end;
        solved[2] = true;
    }
    
    public int[][] getMaze(){
        return this.maze;
    }
    
    public long getTimeToGenerate() {
        return this.timeToGenerate;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public ImageView getImage() {
        return this.image;
    }
    
    public String getGenAlg(){
        return this.genAlg;
    }
    
    public long getTimeBFS(){
        return timeToSolve[0];
    }
    
    public long getTimeAStar(){
        return timeToSolve[1];
    }
    
    public long getTimeIDA(){
        return timeToSolve[2];
    }
    
    public boolean[] getSolved(){
        return solved;
    }
}
