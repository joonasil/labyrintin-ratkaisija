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
public class LinkedList <T>{
    private Node<T> first;
    private Node<T> last;
    private int size;
    
    public LinkedList() {
        first = new Node<>(null,null);
        size = 0;
    }
    
    public int size(){
        return this.size;
    }
      
    public void add(T data) {
        Node<T> l = last;
        Node<T> newNode = new Node(data, null);
        last = newNode;
        if(l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }
    
    public T get(int index) {
        checkIndex(index);
        return node(index).data;
    }
    
    private String outOfBoundsMessage(int index) {
        return "Index: " + index + ", Size: " + size;
    }
    
    private void checkIndex(int index) {
        if(!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException(outOfBoundsMessage(index));
    }
    
    private static class Node<T>{
        T data;
        Node<T> next;
        
        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
    
    Node<T> node(int index) {
        Node<T> x = first;
        for(int i = 0; i < index; i++)
            x = x.next;
        return x;
    } 
}
