/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.util;

import java.util.Random;

/**
 *
 * @author Joonas
 */
public class ArrayList {
    private int[] array;
    private int size;
    private final Random rand;
    
    public ArrayList(Random rand){
        array = new int[20];
        size = 0;
        this.rand = rand;
    }
    
    public void add(int i){
        if(size == array.length)
            resize();
        array[size] = i;
        size++;
    }
    
    public int getRandom(){
        int index = rand.nextInt(size);
        size--;
        int temp = array[size];
        array[size] = array[index];
        array[index] = temp;
        return array[size];
    }
    
    public int pop(){
        size--;
        return array[size];
    }
    
    public boolean isEmpty(){
        return (size == 0);
    }
    
    private void resize(){
        int[] newArray = new int[(int)(size*1.5)];
        for(int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }
}
