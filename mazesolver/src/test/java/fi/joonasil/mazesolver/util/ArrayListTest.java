/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.util;

import java.util.Random;
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
public class ArrayListTest {
    
    private ArrayList list;
            
    public ArrayListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        list = new ArrayList(new Random());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertEquals(true,list.isEmpty());
    }
    
    @Test
    public void testConstructor2() {
        list = new ArrayList(10);
        assertEquals(true,list.isEmpty());
    }
    
    @Test
    public void testAdd() {
        list.add(0);
        assertEquals(1,list.size());
    }
    
    @Test
    public void testAdd2() {
        list.add(0);
        assertEquals(false,list.isEmpty());
    }
    
    @Test
    public void testAdd3() {
        list = new ArrayList(2);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(4,list.size());
    }
    
    @Test
    public void testPop() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3,list.pop());
    }
    
    @Test
    public void testGetRandom() {
        list.add(0);
        assertEquals(0,list.getRandom());
    }
    
    @Test
    public void testGet() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(2,list.get(2));
    }
}
