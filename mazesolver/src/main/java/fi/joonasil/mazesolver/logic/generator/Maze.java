/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

import java.util.LinkedList;
import java.util.Random;
/**
 * Luokka labyrintille.
 * @author Joonas
 */
public class Maze {
    
    private final Path[] maze;
    private final int[][] newMaze;
    private final int x;
    private final int y;
    
    /**
     * 
     * Konstruktori luo uuden labyrintin käyttäen randomisoitua primin algoritmia tällä hetkellä.
     * HUOM! Labyrintin generointi on huomattavasti paljon hitaampi tällä hetkellä, kuin lyhyimmän reitin
     * etsintä. Kun kaikki etsintäalgoritmit ovat valmiit yritän kehittää tehokkaampaa labyrintin generointi algoritmia.
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     * @param rand Satunnaislukugeneraattori labyrintin luomiselle. Pitää olla seedattu, jos haluaa tietyn kokoisesta labyrintista aina samanlaisen.
     */
    public Maze(int x, int y, Random rand) {
        this.x = x;
        this.y = y;
        long start = System.currentTimeMillis();
        maze = generatePrim(rand,x,y);
        long mid = System.currentTimeMillis();
        System.out.println("Time to generate: " + (mid-start));
        newMaze = changeDatatype();
        System.out.println("Time to convert: " + (System.currentTimeMillis()-mid));
    }
    
    /**
     * Generoi labyrintin käyttäen randomisoitua Primin algoritmia.
     * 
     * @param rand Satunnaislukugeneraattori labyrintin luomista varten.
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
    
    /**
     * Metodi muuttaa labyrintin generoimisessa käytetystä tietomuodosta (lista Path- olioita) kaksiuloitteiseksi
     * kokonaislukulistaksi helpompaa käsittelyä ja näytölle piirtämistä.
     * @return Labyrintti kaksiuloitteisena kokonaislukutaulukkona esitettynä.
     */
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
    
    public int[][] getNewMaze() {
        return this.newMaze;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
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
