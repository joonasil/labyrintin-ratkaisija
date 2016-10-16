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
public class PriorityQueueTest {
    
    private PriorityQueue<Integer> queue;
    
    public PriorityQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        queue = new PriorityQueue();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddSmall() {
        queue.add(1);
        assertEquals(Integer.valueOf(1),queue.poll());
    }
    
    @Test
    public void testAddSmall2() {
        queue.add(3);
        queue.add(1);
        queue.add(2);
        assertEquals(Integer.valueOf(1),queue.poll());
    }
    
    @Test
    public void testAddLarge() {
        for(int i = 10000; i > 0; i--){
            queue.add(i);
        }
        assertEquals(Integer.valueOf(1),queue.poll());
    }
    
    @Test
    public void testAddLarge2() {
        for(int i = 1; i < 10000; i++){
            queue.add(i);
        }
        queue.poll();
        assertEquals(Integer.valueOf(2),queue.poll());
    }
    
    @Test
    public void testPoll() {
        queue.add(3);
        queue.add(0);
        queue.add(1);
        queue.add(2);
        queue.poll();
        queue.poll();
        assertEquals(Integer.valueOf(2),queue.poll());
    }
    
    @Test
    public void testPollLarge() {
        for(int i = 10000; i > 0; i--){
            queue.add(i);
        }
        for(int i = 0; i < 9999; i++){
            queue.poll();
        }
        assertEquals(Integer.valueOf(10000), queue.poll());
    }
}
