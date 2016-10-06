/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.util;

/**
 *
 * @author Joonas
 * @param <T>
 */
public class DisjointSet<T> {
    private TreeMap<Set<T>> sets;
    private int size;
    public DisjointSet(){
        sets = new TreeMap<>();
        size = 0;
    }
    
    public void makeSet(T data){
        sets.put(size,new Set(data));
        size++;
    }
    
    private class Set<T>{
        Set<T> parent;
        T data;
        int rank;
        public Set(T data){
            parent = null;
            this.data = data;
            rank = 1;
        }
    }
}
