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
import javafx.scene.image.ImageView;
/**
 * Luokka labyrintille.
 * @author Joonas
 */
public class Maze {
    
    private final long timeToGenerate;
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
        image = ImageConverter.getImage(maze, x, y);
    }
    
    public Maze(int x, int y, int genAlg) {
        Random rand = new Random();
        this.x = x;
        this.y = y;
        long start = System.nanoTime();
        switch (genAlg) {
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
        }
        timeToGenerate = System.nanoTime()-start;
        image = ImageConverter.getImage(maze, x, y);
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
    }
    
    /**
     * Konstruktori luo uuden labyrintin käyttäen randomisoitua primin algoritmiä, jolle on annettu tietty seedi.
     * 
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     * @param seed Seedi labyrintin luomista varten.
     * @param genAlg Mitä generointialgoritmia käytetään.
     */
    public Maze(int x, int y, long seed, int genAlg) {
        Random rand = new Random(seed);
        this.x = x;
        this.y = y;
        long start = System.nanoTime();
        switch (genAlg) {
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
        }
        timeToGenerate = System.nanoTime()-start;
    }
    
    public long solveBreadthFrist() {
        long start = System.nanoTime();
        Solver.breadthFirst(maze);
        long end = System.nanoTime()-start;
        this.image = ImageConverter.getImage(maze, x, y);
        return end;
    }
    
    public long solveAStar() {
        long start = System.nanoTime();
        Solver.aStar(maze);
        long end = System.nanoTime()-start;
        this.image = ImageConverter.getImage(maze, x, y);
        return end;
    }
    
    public long solveIDA() {
        long start = System.nanoTime();
        Solver.IDA(maze);
        long end = System.nanoTime()-start;
        this.image = ImageConverter.getImage(maze, x, y);
        return end;
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
}
