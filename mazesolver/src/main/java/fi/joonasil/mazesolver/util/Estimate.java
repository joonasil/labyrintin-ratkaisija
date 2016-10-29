/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.util;

/**
 *
 * @author Joonas
 */
public class Estimate implements Comparable<Estimate>{
    private final int index;
    private final int estimate;
    private final int length;
    
    public Estimate(int x, int y, int targetX, int targetY, int maxX) {
        estimate = (targetX-x) + (targetY-y);
        index = coordinateToIndex(x,y,maxX);
        length = 0;
    }
    
    public Estimate(int x, int y, int targetX, int targetY, int maxX, int length) {
        estimate = (targetX-x) + (targetY-y) + length;
        index = coordinateToIndex(x,y,maxX);
        this.length = length;
    }
    
    private static int coordinateToIndex(int x, int y, int MaxX) {
        return y*MaxX+x;
    }
    
    public int getIndex() {
        return index;
    }
    
    public int getEstimate() {
        return estimate;
    }
    
    public int getLength() {
        return length;
    }
    
    @Override
    public int compareTo(Estimate other) {
        if(this.getEstimate() == other.getEstimate())
            return 0;
        if(this.getEstimate() > other.getEstimate())
            return 1;
        return -1;
    }
}
