/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.solver;

import fi.joonasil.mazesolver.logic.generator.Maze;
import fi.joonasil.mazesolver.logic.generator.Path;
import java.util.ArrayDeque;

/**
 *
 * @author Joonas
 */
public class Solver {
    
    /**
     * Lyhin reitti labyrintin ylävasemmasta alaoikeaan nurkkaan.
     * @param maze 
     */
    public static void breadthFirst(Maze maze) {
        int size = maze.getMaze().length;
        Path[] path = maze.getMaze();
        boolean visited[] = new boolean[size];
//        int distance[] = new int[size];
        int tree[] = new int[size];
        ArrayDeque<Integer> queue = new ArrayDeque();
//        for(int i = 0; i < size; i++) {
//            distance[i] = Integer.MAX_VALUE;
//        }
        visited[0] = true;
        queue.add(0);
        while(!visited[size-1]) {
            Path current = path[queue.remove()];
            
        }
    }
}
