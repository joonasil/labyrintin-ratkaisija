/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joonas
 */
public class LinkedListTest {
    
    private LinkedList<Integer> list;
    
    public LinkedListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        list = new LinkedList();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertEquals(0,list.size());
    }
    
    @Test
    public void testAddSmall() {
        list.add(1);
        assertEquals(1,list.size());
    }
    
    @Test
    public void testAddLarge() {
        for(int i = 0; i < 10000; i++){
            list.add(i);
        }
        assertEquals(10000,list.size());
    }
    
    @Test
    public void testAddLarge2() {
        for(int i = 0; i < 1000000; i++){
            list.add(i);
        }
        assertEquals(1000000,list.size());
    }
    
    @Test
    public void testGetSmall() {
        list.add(10);
        assertEquals(Integer.valueOf(10),list.get(0));
    }
    
    @Test
    public void testGetSmall2() {
        list.add(10);
        list.add(11);
        assertEquals(Integer.valueOf(11),list.get(1));
    }
    
    @Test
    public void testGetLarge() {
        for(int i = 0; i < 10000; i++){
            list.add(i);
        }
        assertEquals(Integer.valueOf(9876),list.get(9876));
    }
    
    @Test
    public void testRemoveFirst() {
        list.add(10);
        list.add(11);
        assertEquals(Integer.valueOf(10),list.removeFirst());
    }
    
    @Test
    public void testRemoveLast() {
        list.add(10);
        list.add(11);
        assertEquals(Integer.valueOf(11),list.removeLast());
    }
    
    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(Integer.valueOf(2),list.remove(1));
    }
    
    @Test
    public void testRemove2() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(1);
        assertEquals(Integer.valueOf(3),list.remove(1));
    }
    
    @Test
    public void testRemove3() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(1);
        list.remove(1);
        list.remove(0);
        assertEquals(0,list.size());
    }
    
    
}
