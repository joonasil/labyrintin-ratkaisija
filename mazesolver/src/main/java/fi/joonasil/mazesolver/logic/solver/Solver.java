/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.solver;

import fi.joonasil.mazesolver.util.Estimate;
import fi.joonasil.mazesolver.util.PriorityQueue;
import fi.joonasil.mazesolver.util.Queue;

/**
 * Luokka labyrintin ratkaisualgoritmeille.
 * @author Joonas
 */
public class Solver {
    
    /**
     * Metodi etsii lyhimmän reitin labyrintin ylävasemmasta nurkasta alaoikeaan nurkkaan.
     * Etsimiseen käytetään leveyssuuntaista läpikäyntiä.
     * @param maze Labyrintti, josta halutaan löytää lyhin reitti.
     * @return Labyrintti 
     */
    public static void breadthFirst(int[][] path) {
        int x = path.length;
        int y = path[0].length;
        boolean visited[][] = new boolean[x][y];
        int tree[][] = new int[x][y];
        Queue queue = new Queue();
        visited[1][1] = true;
        queue.push(coordinateToIndex(1,1,x));
        while(!visited[x-2][y-2]) {
            int current = queue.pop();
            neighbours(x,current,queue,path,tree,visited);
        }
        shortestPath(path, tree, x, y);
    }
    
    /**
     * Metodi etsii lyhyimmän reitin labyrintin vasemmasta yläkulmasta oikeaan alakulmaan
     * käyttäen A* algoritmia.
     * 
     * @param maze Labyrintti, josta halutaan löytää lyhin reitti.
     * @return 
     */
    public static void aStar(int[][] path) {
        int x = path.length;
        int y = path[0].length;
        boolean visited[][] = new boolean[x][y];
        int tree[][] = new int[x][y];
        PriorityQueue<Estimate> queue = new PriorityQueue();
        visited[1][1] = true;
        queue.add(new Estimate(1,1,x-2,y-2,x));
        while(!visited[x-2][y-2]) {
            int current = queue.poll().getIndex();
            neighbours(x,y,current,queue,path,tree,visited);
        }
        shortestPath(path, tree, x, y);  
    }
    
    private static void neighbours(int x, int current, Queue queue, int[][] path, int[][] tree, boolean[][] visited){
        int currentX;
        int currentY;
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                currentX = indexToX(current,x)+i;
                currentY = indexToY(current,x)+j;
                if(path[currentX][currentY] == 0 || !(j == 0 && i != 0 || j != 0 && i == 0))
                    continue;    
                if(!visited[currentX][currentY]){
                    visited[currentX][currentY] = true;
                    tree[currentX][currentY] =  current;
                    path[currentX][currentY] += 2;
                    queue.push(coordinateToIndex(currentX,currentY,x));
                }
            }
        }
    }
    
    private static void neighbours(int x, int y, int current, PriorityQueue queue, int[][] path, int[][] tree, boolean[][] visited){
        int currentX;
        int currentY;
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                currentX = indexToX(current,x)+i;
                currentY = indexToY(current,x)+j;
                if(path[currentX][currentY] == 0 || !(j == 0 && i != 0 || j != 0 && i == 0))
                    continue;    
                if(!visited[currentX][currentY]){
                    visited[currentX][currentY] = true;
                    tree[currentX][currentY] =  current;
                    path[currentX][currentY] += 3;
                    queue.add(new Estimate(currentX,currentY,x-2,y-2,x));
                }
            }
        }
    }
    
    private static void shortestPath(int[][] path, int[][] tree, int x, int y){
        int current = tree[x-2][y-2];
        path[x-2][y-2] = 11;
        path[1][1] = 11;
        while(current != coordinateToIndex(1,1,x)) {
            path[indexToX(current,x)][indexToY(current,x)] = 11;
            current = tree[indexToX(current,x)][indexToY(current,x)];
        }
    }
    
    /**
     * Metodi tulee todennäköisesti vaihtamaan luokkaa.
     * Muuttaa yksiuloitteisen taulukon indenksin arvon vastaavaksi kaksiuloitteisen taulukon
     * X-koordinaatiksi.
     * 
     * @param index Yksiuloitteisen taulukon indeksi.
     * @param x Kaksiuloitteisen taulukon leveys.
     * @return Indeksiä vastaava kaksiuloitteisen taulukon X-kordinaatti.
     */
    private static int indexToX(int index, int x) {
        return index%x;
    }
     /**
      * Metodi tulee todennäköisesti vaihtamaan luokkaa.
      * Muuttaa yksiuloitteisen taulukon indenksin arvon vastaavaksi kaksiuloitteisen taulukon
      * Y-koordinaatiksi.
      * @param index Yksiuloitteisen taulukon indeksi.
      * @param x Kaksiuloitteisen taulukon leveys.
      * @return Indeksiä vastaava kaksiuloitteisen taulukon Y-kordinaatti.
      */
    private static int indexToY(int index, int x) {
        return index/x;
    }
    
    /**
     * Metodi tulee todennäköisesti vaihtamaan luokkaa.
     * Muuttaa kaksiuloitteisen taulukon koordinaatit yksiuloitteisen taulukon indeksiksi.
     * 
     * @param x Kaksiuloitteisen taulukon X-koordinaatti.
     * @param y Kaksiuloitteisen taulukon Y-koordinaatti.
     * @param MaxX Kaksiuloitteisen taulukon leveys.
     * @return Kaksiuloitteisen taulukon koordinaattia vastaava yksiuloittisen taulukon indeksi.
     */
    private static int coordinateToIndex(int x, int y, int MaxX) {
        return y*MaxX+x;
    }
}
