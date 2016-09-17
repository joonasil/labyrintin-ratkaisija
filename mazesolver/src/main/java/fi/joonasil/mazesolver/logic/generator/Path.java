/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author Joonas
 */
public class Path {
    private boolean partOfMaze;
    private final LinkedList<Wall> walls;
    private final int index;
    private int[] map;
    public Path(int x, int y, int index) {
        int size = x*y;
        walls = new LinkedList<>();
        map = new int[]{0,0,0,0,1,0,0,0,0};
        
        this.index = index;
        if(!(index % x == 0)) 
            walls.add(new Wall(index, index-1));
        if(!((index+1) % x == 0))
            walls.add(new Wall(index, index+1));
        if(!(index < x))
            walls.add(new Wall(index, index-x));
        if(!(index+x >= size))
            walls.add(new Wall(index, index+x));
    }
    
    public void setToMaze(){
        this.partOfMaze = true;
    }
    
    public void markPath() {
        
    }
    
    public boolean isPartOfMaze() {
        return this.partOfMaze;
    }
    
    public int getIndex() {
        return this.index;
    }
    
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
    
    public LinkedList<Wall> getWalls() {
        return this.walls;
    }
    
    public int[] getMap(){
        return this.map;
    }
    
//    public void myWalls() {
//        System.out.print("My walls are: ");
//        for(String s : walls.keySet()) {
//            System.out.print(s + ", ");
//        }
//        System.out.println("");
//    }
//    
//    public void myNeighbours() {
//        System.out.print("My neighbours are: ");
//        for(Wall values : walls.values()) {
//            System.out.print(values.find(this.index) + ", ");
//        }
//        System.out.println("");
//    }
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
