/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

import fi.joonasil.mazesolver.gui.ImageConverter;
import fi.joonasil.mazesolver.logic.solver.Solver;
import java.util.Random;
/**
 * Luokka labyrintille.
 * @author Joonas
 */
public class Maze {
    
    private final long timeToGenerate;
    private final boolean[] solved;
    private final long[] timeToSolve;
    private int[][] maze;
    private final int x;
    private final int y;
    private final String genAlg;
    private int pathLength;
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
        solved = new boolean[3];
        timeToSolve = new long[3];
        pathLength = 0;
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
                maze = Generator.generateKruskal(rand, x, y);
                this.genAlg = "Kruskal's";
                break;
            case 2:
                maze = Generator.generateDFS(rand, x, y);
                this.genAlg = "Depth-first search";
                break;
            default:
                this.genAlg = "Error";
                System.out.println("Error!");
        }
        timeToGenerate = System.nanoTime()-start;
        solved = new boolean[3];
        timeToSolve = new long[3];
        pathLength = 0;
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
        solved = new boolean[3];
        timeToSolve = new long[3];
        pathLength = 0;
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
                System.out.println("Error!");
        }
        timeToGenerate = System.nanoTime()-start;
        solved = new boolean[3];
        timeToSolve = new long[3];
        pathLength = 0;
    }
    
    /**
     * Ratkaisee labyrintin käyttäen breadth-first search algoritmia.
     */
    public void solveBreadthFrist() {
        long start = System.nanoTime();
        Solver.breadthFirst(this);
        long end = System.nanoTime()-start;
        timeToSolve[0] = end;
        solved[0] = true;
    }
    
    /**
     * Ratkaisee labyrintin käyttäen A* algoritmia.
     */
    public void solveAStar() {
        long start = System.nanoTime();
        Solver.aStar(this);
        long end = System.nanoTime()-start;
        timeToSolve[1] = end;
        solved[1] = true;
    }
    
    /**
     * Ratkaisee labyrintin käyttäen iterative deepening A* algoritmia.
     */
    public void solveIDA() {
        long start = System.nanoTime();
        Solver.IDA(this);
        long end = System.nanoTime()-start;
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

    public int getPathLength() {
        return pathLength;
    }

    public void setPathLength(int pathLength) {
        this.pathLength = pathLength;
    }
    
    public int getY() {
        return this.y;
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
    
    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < 2*y+1; i++){
            for(int j = 0; j < 2*x+1; j++){
                s += maze[j][i];
                if(!(maze[j][i] == 11 || maze[j][i] == 10))
                    s += " ";
            }
            s += "\n";
        }
        return s;
    }
}
