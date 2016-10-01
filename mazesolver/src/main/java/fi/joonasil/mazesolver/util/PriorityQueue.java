/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.util;

import java.util.NoSuchElementException;

/**
 *
 * @author Joonas
 * @param <T>
 */
public class PriorityQueue<T extends Comparable<T>> {
    Node<T> root;
    
    public PriorityQueue(){
        root = null;
    }
    
    public void add(T data){
        Node<T> newNode = merge(root,new Node<T>(data));
        if(newNode != root)
            root = newNode;
    }
    
    public T poll(){
        if(root == null)
            throw new NoSuchElementException();
        T out = root.data;
        root = mergePairs(root.subheaps);
        return out;
    }
    
    private Node<T> mergePairs(LinkedList<Node<T>> subheaps){
        if(subheaps.size() == 0)
            return null;
        if(subheaps.size() == 1)
            return subheaps.get(0);
        Node<T> first = subheaps.removeFirst();
        Node<T> second = subheaps.removeFirst();
        return merge(merge(first,second),mergePairs(subheaps));
    }
    
    private Node<T> merge(Node<T> heap1, Node<T> heap2){
        if(heap1 == null)
            return heap2;
        if(heap2 == null)
            return heap1;
        if(heap1.data.compareTo(heap2.data) < 0){
            heap1.subheaps.add(heap2);
            return heap1;
        }
        heap2.subheaps.add(heap1);
        return heap2;
    }
    
    
    private class Node<T>{
        T data;
        LinkedList<Node<T>> subheaps;
        
        public Node(T data){
            this.data = data;
            subheaps = new LinkedList<>();
        }
    }
}
