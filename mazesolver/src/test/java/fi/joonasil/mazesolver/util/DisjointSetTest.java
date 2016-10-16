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
public class DisjointSetTest {
    
    private DisjointSet djs;
    
    public DisjointSetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        djs = new DisjointSet(10);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testFind() {
        assertEquals(0,djs.find(0));
    }
    
    @Test
    public void testUnionSmall() {
        djs.union(0, 1);
        assertEquals(0,djs.find(1));
    }
    
    @Test
    public void testUnionSmall2() {
        djs.union(0, 1);
        djs.union(1, 2);
        assertEquals(0,djs.find(2));
    }
    
    @Test
    public void testUnionSmall3() {
        djs.union(0, 1);
        djs.union(1, 2);
        djs.union(3, 0);
        assertEquals(0,djs.find(3));
    }
    
    @Test
    public void testUnionSmall4() {
        djs.union(0, 1);
        djs.union(1, 2);
        djs.union(3, 4);
        djs.union(3, 5);
        djs.union(3, 0);
        assertEquals(3,djs.find(0));
    }
    
    @Test
    public void testUnionSmall5() {
        djs.union(0, 1);
        djs.union(1, 2);
        djs.union(0, 3);
        djs.union(4, 5);
        djs.union(4, 6);
        djs.union(7, 4);
        djs.union(8, 6);
        djs.union(4, 0);
        assertEquals(4,djs.find(0));
    }
    
    @Test
    public void testUnionSmall6() {
        djs.union(0, 1);
        djs.union(0, 1);
        assertEquals(0,djs.find(1));
    }
    
    @Test
    public void testMaksSet() {
        assertEquals(9,djs.find(9));
    }
    
    @Test
    public void testMaksSet2() {
        assertEquals(5,djs.find(5));
    }
    
    @Test
    public void testUnionLarge() {
        djs = new DisjointSet(10000);
        for(int i = 0; i < 9999; i++){
            djs.union(i, i+1);
        }
        assertEquals(0,djs.find(9999));
    }
    
    @Test
    public void testUnionLarge2() {
        djs = new DisjointSet(1000000);
        for(int i = 0; i < 999999; i++){
            djs.union(i, i+1);
        }
        assertEquals(0,djs.find(999999));
    }
}
