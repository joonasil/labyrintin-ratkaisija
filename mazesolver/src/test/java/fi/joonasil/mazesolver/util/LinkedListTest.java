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
    
    LinkedList<Integer> list;
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
        list = new LinkedList<>();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertEquals(0,list.size());
    }
    
    @Test
    public void testSize() {
        list.add(1);
        assertEquals(1,list.size());
    }
    
    @Test
    public void testGet() {
        list.add(1);
        assertEquals(1,(int)list.get(0));
    }
    
    
}
