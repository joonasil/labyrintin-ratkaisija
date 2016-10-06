/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.util;

import fi.joonasil.mazesolver.logic.generator.Path;
import fi.joonasil.mazesolver.logic.generator.Wall;

import java.util.Random;

/**
 *
 * @author Joonas
 */
public class Generator {
    
    /**
     * Generoi labyrintin käyttäen randomisoitua Primin algoritmia.
     * 
     * @param rand Satunnaislukugeneraattori labyrintin luomista varten.
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     */
    public static int[][] generatePrim(Random rand, int x, int y){
        int size = x*y, first, second, current = rand.nextInt(size), index = 0;
        Path[] output = new Path[size];
        
        for(int i = 0; i < x*y; i++) {
           output[i] = new Path(x,y,i);
        }
        
        output[current].setToMaze();
        TreeMap<Wall> wallMap = new TreeMap<>();
//        HashMap<Integer, Wall> hash = new HashMap<>();
        LinkedList<Wall> walls = output[current].getWalls();
        
        index = addWalls(index, wallMap, walls);
        
        while(!wallMap.isEmpty()) {
            current = rand.nextInt(index);
            first = wallMap.get(current).getFirst();
            second = wallMap.get(current).getSecond();
            index--;
            wallMap.replace(current, wallMap.remove(index));  
            
            if(output[first].isPartOfMaze() ^ output[second].isPartOfMaze()) {
                output[first].openWall(second);
                output[second].openWall(first);
                
                if(output[first].isPartOfMaze() == false) {
                    index = setToMaze(output, first, index, wallMap, walls);
                } else {
                    index = setToMaze(output, second, index, wallMap, walls);
                }
            }
        }
        return changeDatatype(output,x,y);
    }
    
    /**
     * Generoi labyrintin käyttäen depth-first search algoritmia.
     * @param rand Satunnaislukugeneraattori labyrintin luomista varten.
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     * @return 
     */
    public static int[][] generateDFS(Random rand, int x, int y){
        int size = x*y, first, second, current = rand.nextInt(size);
        int unvisited = size-1;
        Path[] output = new Path[size];
        LinkedList<Wall> walls;
        Wall wall;
        Path currentPath;
        LinkedList<Path> stack = new LinkedList<>();
        for(int i = 0; i < x*y; i++) {
           output[i] = new Path(x,y,i);
        }
        (currentPath = output[current]).setToMaze();
        while(unvisited != 0){
            walls = currentPath.getUnopenedWalls();
            removeWalls(walls, output);
            if(walls.size() != 0){
                wall = walls.get(rand.nextInt(walls.size()));
                first = wall.getFirst();
                second = wall.getSecond();
                openWall(first, second, output);
                
                stack.add(currentPath);
                (currentPath = output[second]).setToMaze();
                unvisited--;
            } else if(stack.size() != 0){
                currentPath = stack.removeLast();
            }
        }
        return changeDatatype(output,x,y);
    }
    
    private static void openWall(int first, int second, Path[] output){
        output[first].openWall(second);
        output[second].openWall(first);
    }
    
    private static void removeWalls(LinkedList<Wall> walls, Path[] output){
        for(int i = 0; i < walls.size(); i++){
            if(output[walls.get(i).getFirst()].isPartOfMaze() && output[walls.get(i).getSecond()].isPartOfMaze()){
                walls.remove(i);
                i--;
            }
        }
    }
    
    
    /**
     * Metodi asettaa yhden ruudun osaksi labyrinttiä.
     * 
     * @param output Lista ruuduista, joista muodostuu lopullinen labyrintti.
     * @param current Kyseinen ruutu, mitä ollaan lisäämässä labyrinttiin.
     * @param index Apuindeksi. Pitää kirjaa siitä, kuinka monta labyrintin seinää algoritmilla on valittavissa käytäväksi muutettavaksi.
     * @param wallsOfPathInMaze Lista seinistä, joista aina valitaan yksi tuhottavaksi ja näin luodaan pätkä labyrintin käytävää.
     * @param walls Lisättävänä olevan ruudun seinät, jotka lisätään yllä olevaan listaan.
     * @return 
     */
    private static int setToMaze(Path[] output, int current, int index, TreeMap<Wall> wallMap, LinkedList<Wall> walls) {
        output[current].setToMaze();
        walls = output[current].getWalls();
        return addWalls(index, wallMap, walls);
    }
    
    /**
     * Metodi lisää tietyn ruudun seinät, listaan seinistä, mistä aina valitaan yksi tuhottavaksi.
     * 
     * @param index Sama kuin edellisessä metodissa.
     * @param wallsOfPathInMaze Sama kuin edellisessä metodissa. 
     * @param walls Sama kuin edellisessä metodissa.
     * @return 
     */
    private static int addWalls(int index, TreeMap<Wall> wallMap, LinkedList<Wall> walls) {
        for(int i = 0; i < walls.size(); i++) {
            if(!walls.get(i).isOpen()) {
               wallMap.put(index, walls.get(i));
               index++;
            }
        }
        return index;
    }
    
    /**
     * Metodi muuttaa labyrintin generoimisessa käytetystä tietorakenteesta (lista Path- olioita) kaksiuloitteiseksi
     * kokonaislukulistaksi helpompaa käsittelyä ja näytölle piirtämistä.
     * @return Labyrintti kaksiuloitteisena kokonaislukutaulukkona esitettynä.
     */
    private static int[][] changeDatatype(Path[] maze, int x, int y){
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
}
