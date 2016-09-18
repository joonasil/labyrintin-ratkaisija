/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
/**
 *
 * @author Joonas
 */
public class Maze {
    
    private final Path[] maze;
    private final int x;
    private final int y;
    
    public Maze(int x, int y, Random rand) {
        this.x = x;
        this.y = y;
        maze = generatePrim(rand,x,y);
        System.out.println("Maze generated!");
    }
    
    /**
     * Generoi labyrintin käyttäen randomisoitua Primin algoritmia.
     * 
     * @param rand 
     */
    private Path[] generatePrim(Random rand, int x, int y){
        int size = x*y;
        Path[] output = new Path[size];
        for(int i = 0; i < x*y; i++) {
           output[i] = new Path(x,y,i);
        }
        int current = rand.nextInt(size);
        int first, second;
        output[current].setToMaze();
        LinkedList<Wall> wallOfPathInMaze = new LinkedList<>();
        wallOfPathInMaze.addAll(output[current].getWalls());
        while(!wallOfPathInMaze.isEmpty()) {
            current = rand.nextInt(wallOfPathInMaze.size());
            first = wallOfPathInMaze.get(current).getFirst();
            second = wallOfPathInMaze.get(current).getSecond();
            if(output[first].isPartOfMaze() ^ output[second].isPartOfMaze()) {
                if(output[first].isPartOfMaze() == false) {
                    output[first].setToMaze();
                    wallOfPathInMaze.addAll(output[first].getWalls());
                } else {
                    output[second].setToMaze();
                    wallOfPathInMaze.addAll(output[second].getWalls());
                }
                output[first].openWall(second);
                output[second].openWall(first);
            }
            wallOfPathInMaze.remove(current);
        }
        return output;
    }
    
    private int[][] changeDatatype(){
        int[][] uusi = new int[2*x+1][2*y+1];
        int tempx;
        int tempy = 0;
        int[] map;
        for(int i = 1; i < 2*y; i+=2) {
            tempx = 0;
            for(int j = 1; j < 2*x; j+=2) {
                
                map = maze[tempy*x+tempx].getMap();
                
                uusi[j][i] = 1;
                if(map[3] == 1) 
                    uusi[j-1][i] = 1;

                if(map[1] == 1) 
                    uusi[j][i-1] = 1;

                if(map[7] == 1) 
                    uusi[j][i+1] = 1;

                if(map[5] == 1) 
                    uusi[j+1][i] = 1;
                tempx++;
            }
            tempy++;
        }
        return uusi;
    }
    
    public Path[] getMaze(){
        return this.maze;
    }
    
    public ImageView getImage() {
        int[][] base = changeDatatype();
        WritableImage asd = new WritableImage(2*x+1,2*y+1);
        PixelWriter writer = asd.getPixelWriter();
        for (int i = 0; i < 2*y+1; i++) {
            for (int j = 0; j < 2*x+1; j++) {
                if(base[j][i] == 1) {
                    writer.setColor(j, i, Color.WHITE);
                }
                if(base[j][i] == 0) {
                    writer.setColor(j, i, Color.BLACK);
                }
            }
        }
        return new ImageView(asd);
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
