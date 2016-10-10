/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.util;


import fi.joonasil.mazesolver.gui.ImageConverter;
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
     * @return Generoitu labyrintti.
     */
    public static int[][] generatePrim(Random rand, int x, int y){
        int newX = 2*x+1;
        int newY = 2*y+1;
        int index = (1+((newX)*(1+2*rand.nextInt(y)))+2*rand.nextInt(x));
        
        int[][] maze = new int[newX][newY];
        ArrayList wallIndex = new ArrayList(rand);
       
//        ImageConverter.saveImage(ImageConverter.getImage(maze).getImage());
        
        maze[indexToX(index,newX)][indexToY(index,newX)] = 1;
        
//        ImageConverter.saveImage(ImageConverter.getImage(maze).getImage());
        
        addWalls(newX,newY,wallIndex,index,maze);
       
        while(!wallIndex.isEmpty()){
            index = wallIndex.getRandom();
            setToMaze(newX,newY,index,maze,wallIndex);
        }
        return maze;
    }
    
    /**
     * Asettaa parametrina annetun seinän ja seinän jakavista ruuduista labyrinttiin
     * kuulumattoman ruudun osaksi labyrintin reittiä.
     * @param x labyrintin leveys
     * @param y labyrintin korkeus
     * @param index seinän indeksi
     * @param maze labyrintti
     * @param wallIndex lista seinien indekseistä
     */
    private static void setToMaze(int x, int y, int index, int[][] maze, ArrayList wallIndex){
        if(!(indexToX(index,x) == 1 || indexToX(index,x) == x-2)){
           
            if(maze[indexToX(index,x)-1][indexToY(index,x)] == 1 && maze[indexToX(index,x)+1][indexToY(index,x)] == 0){
                setX(x,y,index,1,maze,wallIndex);
           
            }else if(maze[indexToX(index,x)+1][indexToY(index,x)] == 1 && maze[indexToX(index,x)-1][indexToY(index,x)] == 0){
                setX(x,y,index,-1,maze,wallIndex);
            }    
        }
        if(!(indexToY(index,x) == 1 || indexToY(index,x) == y-2)){
            
            if(maze[indexToX(index,x)][indexToY(index,x)-1] == 1 && maze[indexToX(index,x)][indexToY(index,x)+1] == 0){
                setY(x,y,index,1,maze,wallIndex);
            
            }else if(maze[indexToX(index,x)][indexToY(index,x)+1] == 1 && maze[indexToX(index,x)][indexToY(index,x)-1] == 0){
                setY(x,y,index,-1,maze,wallIndex);
            }     
        } 
    }
    
    private static void setX(int x, int y, int index,int diff, int[][] maze, ArrayList wallIndex){
        int newx = indexToX(index,x)+diff;
        int newy = indexToY(index,x);
        maze[newx][newy] = 1;
        addWalls(x,y,wallIndex,coordinateToIndex(newx,newy,x),maze);
        maze[indexToX(index,x)][indexToY(index,x)] = 1;
//        ImageConverter.saveImage(ImageConverter.getImage(maze).getImage());
    }
    
    private static void setY(int x, int y, int index,int diff, int[][] maze, ArrayList wallIndex){
        int newx = indexToX(index,x);
        int newy = indexToY(index,x)+diff;
        maze[newx][newy] = 1;
        addWalls(x,y,wallIndex,coordinateToIndex(newx,newy,x),maze);
        maze[indexToX(index,x)][indexToY(index,x)] = 1;
//        ImageConverter.saveImage(ImageConverter.getImage(maze).getImage());
    }
    
    /**
     * Metodi lisää labyrinttiin lisätyn ruudun seinät listalle
     * @param x labyrintin leveys
     * @param y labyrintin korkeus
     * @param wallIndex lista seinistä
     * @param index lisätyn ruudun indeksi
     * @param maze labyrintti
     */
    private static void addWalls(int x, int y, ArrayList wallIndex, int index, int[][] maze){
        if(!(indexToX(index,x) == 1)){
            if(maze[indexToX(index,x)-2][indexToY(index,x)] == 0)
                wallIndex.add(index-1);
        }
        if(!(indexToX(index,x) == x-2)){
            if(maze[indexToX(index,x)+2][indexToY(index,x)] == 0)
                wallIndex.add(index+1);
        }
        if(!(indexToY(index,x) == 1)){
            if(maze[indexToX(index,x)][indexToY(index,x)-2] == 0)
                wallIndex.add(index-x);
        }
        if(!(indexToY(index,x) == y-2)){
            if(maze[indexToX(index,x)][indexToY(index,x)+2] == 0)
                wallIndex.add(index+x);
        }
    }
    
    /**
     * Muuttaa yksiuloitteisen taulukon indeksin kaksiuloitteisen taulukon x-koordinaatiksi.
     * @param index yksiuloitteisen taulukon indeksi.
     * @param x Kaksiuloitteisen taulukon leveys.
     * @return Vastaava kaksiuloitteisen taulukon x-koordinaatti.
     */
    private static int indexToX(int index, int x) {
        return index%x;
    }
    
    /**
     * Muuttaa yksiuloitteisen taulukon indeksin kaksiuloitteisen taulukon y-koordinaatiksi.
     * @param index yksiuloitteisen taulukon indeksi.
     * @param x Kaksiuloitteisen taulukon leveys.
     * @return Vastaava kaksiuloitteisen taulukon y-koordinaatti.
     */
    private static int indexToY(int index, int x) {
        return index/x;
    }
    
    /**
     * Muuttaa kaksiuloitteisen taulukon (x,y)-koordinaatin yksiuloitteisen taulukon indeksiksi.
     * @param x Kaksiuloitteisen taulukon x-koordinaatti.
     * @param y Kaksiuloitteisen taulukon y-koordinaatti.
     * @param MaxX Kaksiuloitteisen taulukon leveys.
     * @return Vastaava yksiuloitteisen taulukon indeksi.
     */
    private static int coordinateToIndex(int x, int y, int MaxX) {
        return y*MaxX+x;
    }
    
    /**
     * Generoi labyrintin käyttäen depth-first search algoritmia.
     * @param rand Satunnaislukugeneraattori labyrintin luomista varten.
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     * @return labyrintti
     */
    public static int[][] generateDFS(Random rand, int x, int y){
        int newX = 2*x+1;
        int newY = 2*y+1;
        int unvisited = x*y-1;
        int index = (1+((newX)*(1+2*rand.nextInt(y)))+2*rand.nextInt(x));
        int wall;
        int[][] maze = new int[newX][newY];
                
//        ImageConverter.saveImage(ImageConverter.getImage(maze).getImage());
        
        ArrayList stack = new ArrayList(rand);
        ArrayList neighbours;
        
        maze[indexToX(index,newX)][indexToY(index,newX)] = 1;
        
//        ImageConverter.saveImage(ImageConverter.getImage(maze).getImage());
        
        while(unvisited != 0){
            neighbours = unvisitedNeighbours(newX,newY,index,maze,rand);
            
            if(!neighbours.isEmpty()){
                wall = neighbours.getRandom();
                stack.add(index);
                maze[indexToX(wall,newX)][indexToY(wall,newX)] = 1;
//                ImageConverter.saveImage(ImageConverter.getImage(maze).getImage());
                index = newIndex(index,wall,newX);
                maze[indexToX(index,newX)][indexToY(index,newX)] = 1;
//                ImageConverter.saveImage(ImageConverter.getImage(maze).getImage());
                unvisited--;
            } else if(!stack.isEmpty()){
                index = stack.pop();
            }
        }
        return maze;
    }
    
    private static int newIndex(int index, int wall, int x){
        if(index-wall == x)
            return index-(2*x);
        if(index-wall == 1)
            return index-2;
        if(index-wall == -1)
            return index+2;
        if(index-wall == -x)
            return index+(2*x);
        return 0;
    }
    
    private static ArrayList unvisitedNeighbours(int x, int y, int index, int[][] maze, Random rand){
        ArrayList unvisited = new ArrayList(rand);
        if(!(indexToX(index,x) == 1)){
            if(maze[indexToX(index,x)-2][indexToY(index,x)] == 0)
                unvisited.add(index-1);
        }
        if(!(indexToX(index,x) == x-2)){
            if(maze[indexToX(index,x)+2][indexToY(index,x)] == 0)
                unvisited.add(index+1);
        }
        if(!(indexToY(index,x) == 1)){
            if(maze[indexToX(index,x)][indexToY(index,x)-2] == 0)
                unvisited.add(index-x);
        }
        if(!(indexToY(index,x) == y-2)){
            if(maze[indexToX(index,x)][indexToY(index,x)+2] == 0)
                unvisited.add(index+x);
        }
        return unvisited;
    }
    
    public static int[][] generateKruskal(Random rand, int x, int y){
        int newX = 2*x+1;
        int newY = 2*y+1;
        int size = x*y;
        int index;
        int[][]maze = new int[newX][newY];
        
        ArrayList walls = new ArrayList(rand);
        
        for(int i = 0; i < size; i++){
            index = smallToBig(i,x,newX);
            maze[indexToX(index,newX)][indexToY(index,newX)] = 1;
            if(!(indexToX(index,newX) == newX-2))
                walls.add(index+1);
            if(!(indexToY(index,newX) == newY-2))
                walls.add(index+newX);
        }
//        ImageConverter.saveImage(ImageConverter.getImage(maze).getImage());
        DisjointSet cells = new DisjointSet(size);
        
        while(!walls.isEmpty()){
            index = walls.getRandom();
            if(maze[indexToX(index,newX)+1][indexToY(index,newX)] == 1){
                if(cells.find(bigToSmall(index-1,x,newX)) != cells.find(bigToSmall(index+1,x,newX))){
                    maze[indexToX(index,newX)][indexToY(index,newX)] = 1;
//                    ImageConverter.saveImage(ImageConverter.getImage(maze).getImage());
                    cells.union(bigToSmall(index-1,x,newX), bigToSmall(index+1,x,newX));
                }
            }else{
                if(cells.find(bigToSmall(index-newX,x,newX)) != cells.find(bigToSmall(index+newX,x,newX))){
                    maze[indexToX(index,newX)][indexToY(index,newX)] = 1;
//                    ImageConverter.saveImage(ImageConverter.getImage(maze).getImage());
                    cells.union(bigToSmall(index-newX,x,newX), bigToSmall(index+newX,x,newX));
                }
            }
        }
        return maze;
    }
    
    private static int bigToSmall(int index, int smallX, int bigX){
        int x = ((index%bigX)-1)/2;
        int y = ((index/bigX)-1)/2;
        return y*smallX+x;
    }
    
    private static int smallToBig(int index, int smallX, int bigX){
        int x = index%smallX;
        int y = index/smallX;
        return (1+((bigX)*(1+2*y))+2*x);
    }
}
