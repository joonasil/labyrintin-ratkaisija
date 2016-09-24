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
public class Queue{
    
    private int[] queue;
    private int head;
    private int tail;
    
    public Queue() {
       queue = new int[16];
       head = 0;
       tail = 0;
    }
    
    public Queue(int initSize) {
        queue = new int[initSize];
        head = 0;
        tail = 0;
    }
    
    public void push(int i) {
        queue[head] = i;
        head++;
        if(head == queue.length)
            head = 0;
        if(head + 1 == tail)
            allocateSpace();
        
    }
    
    public int pop() {
        int out = queue[tail];
        tail++;
        if(tail == queue.length)
            tail = 0;
        return out;
    }
    
    private void allocateSpace() {
        int[] temp = queue;
        int length = queue.length;
        if(length*2 < Integer.MAX_VALUE)
            queue = new int[length*2];
        else
            queue = new int[Integer.MAX_VALUE];
        
        for(int i = 0; i < length-1; i++) {
            queue[i] = temp[i];
        }
    }
    
    
}
