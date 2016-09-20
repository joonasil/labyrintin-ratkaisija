/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.solver;

import fi.joonasil.mazesolver.logic.generator.Maze;
import fi.joonasil.mazesolver.logic.generator.Path;
import fi.joonasil.mazesolver.util.Estimate;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * Luokka labyrintin ratkaisualgoritmeille.
 * @author Joonas
 */
public class Solver {
    
    /**
     * Metodi etsii lyhimmän reitin labyrintin ylävasemmasta nurkasta alaoikeaan nurkkaan.
     * Etsimiseen käytetään leveyssuuntaista läpikäyntiä.
     * @param maze Labyrintti, josta halutaan löytää lyhin reitti.
     */
    public static int[][] breadthFirst(Maze maze) {
        long start = System.currentTimeMillis();
        int x = 2*maze.getX()+1;
        int y = 2*maze.getY()+1;
        int currentX;
        int currentY;
        int[][] path = maze.getNewMaze();
        boolean visited[][] = new boolean[x][y];
//        int distance[] = new int[size];
        int tree[][] = new int[x][y];
        ArrayDeque<Integer> queue = new ArrayDeque();
//        for(int i = 0; i < size; i++) {
//            distance[i] = Integer.MAX_VALUE;
//        }
        visited[1][1] = true;
        path[1][1] += 2;
        queue.add(coordinateToIndex(1,1,x));
        while(!visited[x-2][y-2]) {
            int current = queue.remove();
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
                        queue.add(coordinateToIndex(currentX, currentY, x));
                    }
                }
            }
        }
        int current = tree[x-2][y-2];
        path[x-2][y-2] = 11;
        path[1][1] = 11;
        while(current != coordinateToIndex(1,1,x)) {
            path[indexToX(current,x)][indexToY(current,x)] = 11;
            current = tree[indexToX(current,x)][indexToY(current,x)];
        }
        System.out.println("Time to solve bfs: " + (System.currentTimeMillis()-start));
        return path;  
    }
    
    /**
     * Muuta queue -> priorityQueue ja laske jokaiselle ruudulle etäisyysarvio, mikä
     * laitetaan kyseiseen jonoon. etäisyysarvion pitää viitata jotenkin kyseiseen
     * ruutuun. eteisyysarvio ei ole yksikäsitteinen. kahdella ruudulla voi olla sama
     * etäisyysarivo. aputietorakenne tarpeellinen!
     * 
     * @param maze
     * @return 
     */
    public static int[][] aStar(Maze maze) {
        long start = System.currentTimeMillis();
        int x = 2*maze.getX()+1;
        int y = 2*maze.getY()+1;
        int currentX;
        int currentY;
        int[][] path = maze.getNewMaze();
        boolean visited[][] = new boolean[x][y];
//        int distance[] = new int[size];
        int tree[][] = new int[x][y];
        PriorityQueue<Estimate> queue = new PriorityQueue();
//        for(int i = 0; i < size; i++) {
//            distance[i] = Integer.MAX_VALUE;
//        }
        visited[1][1] = true;
        path[1][1] += 3;
        queue.add(new Estimate(1,1,x-2,y-2,x));
        while(!visited[x-2][y-2]) {
            int current = queue.poll().getIndex();
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
        int current = tree[x-2][y-2];
        path[x-2][y-2] = 11;
        path[1][1] = 11;
        while(current != coordinateToIndex(1,1,x)) {
            path[indexToX(current,x)][indexToY(current,x)] = 11;
            current = tree[indexToX(current,x)][indexToY(current,x)];
        }
        System.out.println("Time to solve a*: " + (System.currentTimeMillis()-start));
        return path;  
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
