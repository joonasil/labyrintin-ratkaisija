/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.solver;

import fi.joonasil.mazesolver.Mazesolver;
import fi.joonasil.mazesolver.util.ArrayList;
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
     * @param path Labyrintti, josta halutaan löytää lyhin reitti.
     */
    public static void breadthFirst(int[][] path) {
        int x = path.length; /*O(1)*/
        int y = path[0].length; /*O(1)*/
        int current;
        boolean visited[][] = new boolean[x][y]; /*O(x*y)*/
        int tree[][] = new int[x][y]; /*O(x*y)*/
        Queue queue = new Queue(); /*O(1)*/
        visited[1][1] = true; /*O(1)*/
        queue.push(coordinateToIndex(1,1,x)); /*O(1)*/
        while(!visited[x-2][y-2]) { /*O(x*y) worst case O(x+y) best case*/
            current = queue.pop(); /*O(1)*/
            neighbours(x,current,queue,path,tree,visited); /*O(1)*/
        }
        shortestPath(path, tree, x, y); /*O(x+y)*/
    }
    
    /**
     * Metodi etsii lyhyimmän reitin labyrintin vasemmasta yläkulmasta oikeaan alakulmaan
     * käyttäen A* algoritmia.
     * 
     * @param path Labyrintti, josta halutaan löytää lyhin reitti.
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
    
    /**
     * Metodi etsii labyrintista lyhyimmän reitin käyttäen iterative deepening A* algoritmia.
     * @param maze labyrintti, josta halutaan löytää lyhin reitti.
     */
    public static void IDA(int[][] maze){
        int x = maze.length;
        int y = maze[0].length;
        int bound = (x-3) + (y-3);
        int index = coordinateToIndex(1,1,x);
        int t = 0;
        
        while(t != -1){
            t = search(index,0,0,bound,x,y,maze);
            bound = t;
        }
    }
    
    /**
     * Iterative deepening A* algoritmin käyttämä rekursiivinen funktio.
     * @param index nykyinen labyrintin indeksi
     * @param prev edellinen labyrintin indeksi
     * @param cost alusta nykyiseen indeksiin johtavan reitin pituus
     * @param boun reitin maksimipituus
     * @param x labyrintin leveys
     * @param y labyrintin korkeus
     * @param maze labyrintti
     * @return -1 jos reitti haluttuun pisteeseen on löytynyt, muuten pienin liian pitkä reitti, mikä karsittiin pois.
     */
    private static int search(int index, int prev, int cost, int bound, int x, int y, int[][] maze){
        int estimate = ((x-2-indexToX(index,x)) + (y-2-indexToY(index,x)));
        int f = cost + estimate;
        int test = maze[indexToX(index,x)][indexToY(index,x)];
        if(test == 1 || test == 3 || test == 4 || test == 6){
            markVisited(index,prev,maze,x);
            maze[indexToX(index,x)][indexToY(index,x)] += 4;
        }
        if(f > bound)
            return f;
        if(index == coordinateToIndex(x-2,y-2,x)){
            maze[indexToX(index,x)][indexToY(index,x)] = 11;
            markShortest(index,prev,maze,x);
            Mazesolver.getMaze().setPathLength((cost*2));
            return -1;
        }
        int min = Integer.MAX_VALUE;
        int t;
        ArrayList succ = successors(index,prev,maze,x,y);
        for(int i = 0; i < succ.size(); i++){
            t = search(succ.get(i),index,cost+1,bound,x,y,maze);
            if(t == -1){
                maze[indexToX(index,x)][indexToY(index,x)] = 11;
                markShortest(index,prev,maze,x);
                return -1;
            }
            if(t < min)
                min = t;
        }
        return min;
    }
    
    private static void markVisited(int index, int prev, int[][] maze, int x){
        if(prev == index-2)
            maze[indexToX(index,x)-1][indexToY(index,x)] += 4;
        if(prev == index+2)
            maze[indexToX(index,x)+1][indexToY(index,x)] += 4;
        if(prev == index-(2*x))
            maze[indexToX(index,x)][indexToY(index,x)-1] += 4;
        if(prev == index+(2*x))
            maze[indexToX(index,x)][indexToY(index,x)+1] += 4;
    }
    
    private static void markShortest(int index, int prev, int[][] maze, int x){
        if(prev == index-2)
            maze[indexToX(index,x)-1][indexToY(index,x)] = 11;
        if(prev == index+2)
            maze[indexToX(index,x)+1][indexToY(index,x)] = 11;
        if(prev == index-(2*x))
            maze[indexToX(index,x)][indexToY(index,x)-1] = 11;
        if(prev == index+(2*x))
            maze[indexToX(index,x)][indexToY(index,x)+1] = 11;
    }
    
    /**
     * Palauttaa listan parametrina annetun indeksin naapuriruuduista, joihin rekursivinen funktio voi mennä.
     * @param index nykyinen indeksi
     * @param prev edelinen indeksi
     * @param maze labyrintti
     * @param x labyrintin leveys
     * @param y labyrintin korkeus
     * @return lista naapuriruuduista
     */
    private static ArrayList successors(int index, int prev, int[][] maze, int x, int y){
        ArrayList adj = new ArrayList(3);
        
        if(!(indexToX(index,x) == x-2)){
            if(maze[indexToX(index,x)+1][indexToY(index,x)] != 0 && prev != index+2)
                adj.add(index+2);
        }
        if(!(indexToY(index,x) == y-2)){
            if(maze[indexToX(index,x)][indexToY(index,x)+1] != 0 && prev != index+(2*x)) 
                adj.add(index+(2*x));
        }
        if(!(indexToY(index,x) == 1)){
            if(maze[indexToX(index,x)][indexToY(index,x)-1] != 0 && prev != index-(2*x))
                adj.add(index-(2*x));
        }
        if(!(indexToX(index,x) == 1)){
            if(maze[indexToX(index,x)-1][indexToY(index,x)] != 0 && prev != index-2)
                adj.add(index-2);
        }
        return adj;
    }
    
    /**
     * Lisää jonoon bfs algoritmin tämänhetkisen indeksin viereiset indeksin, joita ei ole vielä
     * käyty läpi algoritmin toimesta.
     * @param x Labyrintin leveys.
     * @param current Algoritmin tämänhetkinen indeksi.
     * @param queue Jono, josta algoritmi ottaa seuraavan indeksin tarkasteltavaksi.
     * @param path Labyrintti kaksiuloitteisena kokonaislukutaulukona esitettynä.
     * @param tree Kaksiuloitteinen lista, mikä tallentaa lyhyimmän reitin labyrintin läpi.
     * @param visited Kaksiuloitteinen lista, mikä pitää kirjaa vierailluista indekseistä.
     */
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
    
    /**
     * Lisää prioriteettijonoon a* algoritmin tämänhetkisen indeksin viereiset indeksin, joita ei ole vielä
     * käyty läpi algoritmin toimesta.
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     * @param current Algoritmin tämänhetkinen indeksi.
     * @param queue Prioriteettijono, josta algoritmi ottaa seuraavan indeksin tarkasteltavaksi.
     * @param path Labyrintti kaksiuloitteisena kokonaislukutaulukona esitettynä.
     * @param tree Kaksiuloitteinen lista, mikä tallentaa lyhyimmän reitin labyrintin läpi.
     * @param visited Kaksiuloitteinen lista, mikä pitää kirjaa vierailluista indekseistä.
     */
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
    
    /**
     * "Piirtää" lyhimmän reitin labyrinttiin.
     * @param path Labyrintti kaksiuloitteisena kokonaislukutaulukona esitettynä.
     * @param tree Kaksiuloitteinen lista, mikä tallentaa lyhyimmän reitin labyrintin läpi.
     * @param x Labyrintin leveys.
     * @param y Labyrintin korkeus.
     */
    private static void shortestPath(int[][] path, int[][] tree, int x, int y){
        int length = 1;
        int current = tree[x-2][y-2];
        path[x-2][y-2] = 11;
        path[1][1] = 11;
        while(current != coordinateToIndex(1,1,x)) {
            path[indexToX(current,x)][indexToY(current,x)] = 11;
            current = tree[indexToX(current,x)][indexToY(current,x)];
            length++;
        }
        Mazesolver.getMaze().setPathLength(length);
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
