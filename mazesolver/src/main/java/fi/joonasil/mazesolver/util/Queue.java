
package fi.joonasil.mazesolver.util;

import java.util.NoSuchElementException;

/**
 * Yksinkertainen jono tietorakenne käyttäen javan array tietorakennetta.
 * Jonoon voi laittaa vain kokonaislukuja, koska tarvitsen sitä vain indekseille.
 * 
 * @author Joonas
 */
public class Queue{
    
    private int[] queue;
    private int tail;
    private int head;
    
    /**
     * Konstruktori luo uuden jonon, jonka koko on 16.
     */
    public Queue() {
       queue = new int[16];
       tail = 0;
       head = 0;
    }
    
    /**
     * Konstruktori luo uuden jonon, jonka koko on parametrina annettu.
     * @param initSize Jonon koko.
     */
    public Queue(int initSize) {
        queue = new int[initSize];
        tail = 0;
        head = 0;
    }
    
    /**
     * Lisää luvun jonon perälle. Jos jono on täynnä, niin kopioi jonon suurempaan listaan.
     * @param i 
     */
    public void push(int i) {
        queue[tail] = i;
        tail++;
        if(tail == queue.length)
            tail = 0;
        if(tail + 1 == head || (head == 0 && tail == queue.length-1))
            allocateSpace();
        
    }
    
    /**
     * Palauttaa jonon ensimmäisenä olevan luvun.
     * @return 
     */
    public int pop() {
        if(this.isEmpty()) {
            throw new NoSuchElementException();
        }
        int out = queue[head];
        head++;
        if(head == queue.length)
            head = 0;
        return out;
    }
    
    /**
     * Palauttaa totuusarvon siitä, onko jono tyhjä.
     * @return totuusarvo, joka kertoo onko lista tyhjä.
     */
    public boolean isEmpty() {
        return head == tail;
    }
    
    /**
     * Kopioi jonon suurempaan listaan.
     */
    private void allocateSpace() {
        int[] temp = queue;
        int length = queue.length;
            
        queue = new int[length*2];
        
        int x = head;
        for(int i = 0; i < length-1; i++) {
            queue[i] = temp[x];
            x++;
            if(x == length)
                x = 0;
            if(x == tail) {
                tail = i+1;
                break;
            }
        }
        head = 0;
    }
    
    
}
