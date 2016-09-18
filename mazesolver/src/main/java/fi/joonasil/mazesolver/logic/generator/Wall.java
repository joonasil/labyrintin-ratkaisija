/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

/**
 *
 * @author Joonas
 */
public class Wall {
    private boolean opened;
    private final int first;
    private final int second;
    
    public Wall(int first, int second) {
        this.first = first;
        this.second = second;
        opened = false;
    }
    
    public int find(int adjacent) {
        if(adjacent == first)
            return second;
        if(adjacent == second)
            return first;
        return -1;
    }
    
    public boolean isOpen() {
        return opened;
    }
    
    public void open() {
        opened = true;
    }
    
    public boolean belongsTo(int path) {
        return path == first || path == second;
    }
    
    public boolean inBetween(int a, int b){
        return (first == a && second == b || first == b && second == a);           
    }
    
    public int getFirst() {
        return this.first;
    }
    
    public int getSecond() {
        return this.second;
    }
}
