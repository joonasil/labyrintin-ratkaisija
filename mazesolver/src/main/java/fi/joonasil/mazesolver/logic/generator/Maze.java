/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

import fi.joonasil.mazesolver.gui.ImageConverter;
import fi.joonasil.mazesolver.logic.solver.Solver;
import fi.joonasil.mazesolver.util.Generator;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;
import javafx.scene.image.ImageView;
/**
 * Luokka labyrintille.
 * @author Joonas
 */
public class Maze {
    
    private ImageView image;
    private int[][] maze;
    private final int x;
    private final int y;
    
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
        long start = System.currentTimeMillis();
        maze = Generator.generatePrim(rand, x, y);
        System.out.println("Time to generate: " + (System.currentTimeMillis()-start));
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
        long start = System.currentTimeMillis();
        maze = Generator.generatePrim(rand,x,y);
        System.out.println("Time to generate: " + (System.currentTimeMillis()-start));
    }
    
    public void solveBreadthFrist() {
        this.maze = Solver.breadthFirst(maze);
        this.image = ImageConverter.getImage(maze, x, y);
    }
    
    public void solveAStar() {
        this.maze = Solver.aStar(maze);
        this.image = ImageConverter.getImage(maze, x, y);
    }
    
    public int[][] getMaze(){
        return this.maze;
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
    
    /**
     * Apumetodi metodille toString, koska jokaisen ruudun esittämiseen tarvitaan
     * ainakin tällä hetkellä 3x3 ruudukko merkkejä. 
     * 
     * @param i Mikä kolmesta labyrintin yhdellä rivillä olevien ruutujen riveistä
     * halutaan.
     * @param currentRow Millä labyritin rivillä mennään.
     * @return Yksi labyrintin rivi acii koodina.
     */
//    private String getRow(int i, int currentRow){
//        String row = "";
//        int[] temp = new int[9];
//        for(int k = currentRow*x; k < currentRow*x+x; k++){
//            temp = output[k].getMap();
//            for(int j = (i*3); j < (i*3)+3; j++) {
//                row += "" + temp[j];
//            }
//        }
//        return row;
//    }
    
   /**
    *  Jee se toimii! Metodi palauttaa acii esityksen labyrintistä. 
    * 
    * @return Labyrintti acii koodina.
    */
//    public String toString() {
//        String output = "";
//        for(int i = 0; i < y; i++) {
//            for(int j = 0; j < 3; j++) {
//                output += getRow(j,i) + "\n";
//            }
//        }        
//        return output;
//    }
}
