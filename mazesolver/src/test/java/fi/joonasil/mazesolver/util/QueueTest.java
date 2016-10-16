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
public class QueueTest {
    
    Queue queue;
    public QueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        queue = new Queue();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertEquals(true,queue.isEmpty());
    }
    
    @Test
    public void testConstructor2() {
        queue = new Queue(2);
        assertEquals(true,queue.isEmpty());
    }
    
    @Test
    public void testIsEmpty() {
        queue.push(1);
        assertEquals(false,queue.isEmpty());
    }
    
    @Test
    public void testPop() {
        queue.push(1);
        assertEquals(1,queue.pop());
    }
    
    @Test
    public void testAllocateSpace() {
        queue = new Queue(2);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertEquals(1,queue.pop());
    }
    
    @Test
    public void testAllocateSpace2() {
        queue = new Queue(2);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        queue.pop();
        queue.pop();
        queue.pop();
        queue.pop();
        assertEquals(5,queue.pop());
    }
    
}
