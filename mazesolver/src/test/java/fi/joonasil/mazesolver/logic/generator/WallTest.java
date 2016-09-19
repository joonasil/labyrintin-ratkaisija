/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

import fi.joonasil.mazesolver.logic.generator.Wall;
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
public class WallTest {
    
    Wall wall;
    public WallTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        wall = new Wall(0,1);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testConstructor() {
        assertEquals(0, wall.getFirst());
    }
    
    @Test
    public void testConstructor2() {
        assertEquals(1, wall.getSecond());
    }
    
    @Test
    public void testConstructor3() {
        assertEquals(false, wall.isOpen());
    }
    
    @Test
    public void testFind() {
        assertEquals(0, wall.find(1));
    }
    
    @Test
    public void testFind2() {
        assertEquals(1, wall.find(0));
    }
    
    @Test
    public void testFind3() {
        assertEquals(-1, wall.find(2));
    }
    
    @Test
    public void testOpen() {
        wall.open();
        assertEquals(true, wall.isOpen());
    }
    
    @Test
    public void testBelongsTo() {
        assertEquals(true, wall.belongsTo(0));
    }
    
    @Test
    public void testBelongsTo2() {
        assertEquals(true, wall.belongsTo(1));
    }
    
    @Test
    public void testBelongsTo3() {
        assertEquals(false, wall.belongsTo(2));
    }
    
    @Test
    public void testInBetween() {
        assertEquals(true, wall.inBetween(0, 1));
    }
    
    @Test
    public void testInBetween2() {
        assertEquals(true, wall.inBetween(1, 0));
    }
    
    @Test
    public void testInBetween3() {
        assertEquals(false, wall.inBetween(0, 0));
    }
    
    @Test
    public void testInBetween4() {
        assertEquals(false, wall.inBetween(-1, 7));
    }
    
}
