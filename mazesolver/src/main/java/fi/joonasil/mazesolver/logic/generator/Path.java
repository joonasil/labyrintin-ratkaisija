/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

import java.util.LinkedList;

/**
 * Luokka yksittäiselle labyrintin pisteelle. Käytetään vain labyrintin generoimisessa. Valmis labyrintti
 * muutetaan kaksiulotteiseksi kokonaislukutaulukoksi. Jos kehitän paremman keinon luoda labyrintin tämä
 * luokka saattaa muuttua/poistua.
 * @author Joonas
 */
public class Path {
    private boolean partOfMaze;
    private final LinkedList<Wall> walls;
    private final int index;
    private int[] map;
    
    /**
     * Luo uuden labyrintin palasen.
     * 
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     * @param index Kyseisen palasen indeksi labyrintissä.
     */
    public Path(int x, int y, int index) {
        walls = new LinkedList<>();
        map = new int[]{0,0,0,0,1,0,0,0,0};
        
        this.index = index;
        if(!(index % x == 0)) 
            walls.add(new Wall(index, index-1));
        if(!((index+1) % x == 0))
            walls.add(new Wall(index, index+1));
        if(!(index < x))
            walls.add(new Wall(index, index-x));
        if(!(index+x >= x*y))
            walls.add(new Wall(index, index+x));
    }
    
    /**
     * Metodi asettaa palasen osaksi labyrinttiä.
     */
    public void setToMaze(){
        this.partOfMaze = true;
    }
    
    /**
     * Metodi tarkistaa onko palanen osa labyrinttiä.
     * @return Totuusarvo siitä, onko pala osa labyrinttiä.
     */
    public boolean isPartOfMaze() {
        return this.partOfMaze;
    }
    
    /**
     * Metodi palauttaa ruudun paikan labyrintissä.
     * @return Ruudun paikka labyrintissä.
     */
    public int getIndex() {
        return this.index;
    }
    
    /**
     * Metodi avaa ruudun seinän parametrinä annettuun ruutuun, luoden näin polun
     * kyseisten ruutujen välille.
     * @param adjacent Viereinen ruutu, johon halutaan saada polku.
     */
    public void openWall(int adjacent) {
        for(Wall wall : this.getWalls()){
            if(wall.inBetween(index, adjacent)){
                wall.open();
                if(index-adjacent == 1) 
                    map[3] = 1;
                else if(index-adjacent == -1) 
                    map[5] = 1;
                else if(index-adjacent > 1) 
                    map[1] = 1;
                else
                    map[7] = 1;   
            }
        }
    }
    
    /**
     * Metodi palauttaa listan ruudun seinistä.
     * @return Lista ruudun seinistä.
     */
    public LinkedList<Wall> getWalls() {
        return this.walls;
    }
    
    /**
     * Metodi palauttaa kokonaislukuesityksen ruudusta.
     * @return Lista kokonaislukuja, jotka esittävät ruudun tilaa.
     */
    public int[] getMap(){
        return this.map;
    }
    
    public String toString() {
        String output = index + ":\n";
        for(int i = 1; i < 10; i++) {
            output += map[i-1];
            if(i % 3 == 0)
                output += "\n";
        }
        return output;
    }
}
