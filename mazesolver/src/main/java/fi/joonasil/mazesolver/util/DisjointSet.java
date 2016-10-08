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
public class DisjointSet {
    private final int[] rank;
    private final int[] parent;
    private final int size;
    
    public DisjointSet(int size){
        rank = new int[size];
        parent = new int[size];
        this.size = size;
        makeSet();
    }
    
    private void makeSet(){
        for(int i = 0; i < size; i++)
            parent[i] = i;
    }
    
    public int find(int x){
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }
    
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        
        if(rootX == rootY)
            return;
        
        if(rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if(rank[rootY] < rank[rootX]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX] = rank[rootX]+1;
        }
    }
}
