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
public class EstimateTest {
    
    Estimate estimate;
    
    public EstimateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        estimate = new Estimate(1,1,2,2,3);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetIndex() {
        assertEquals(4,estimate.getIndex());
    }
    
    @Test
    public void testGetEstimate() {
        assertEquals(2,estimate.getEstimate());
    }
    
    @Test
    public void testCompareTo() {
        Estimate second = new Estimate(1,1,3,3,4);
        assertEquals(1,second.compareTo(estimate));
    }
    
    @Test
    public void testCompareTo2() {
        assertEquals(0,estimate.compareTo(estimate));
    }
    
    @Test
    public void testCompareTo3() {
        Estimate second = new Estimate(1,1,3,3,4);
        assertEquals(-1,estimate.compareTo(second));
    }
}
