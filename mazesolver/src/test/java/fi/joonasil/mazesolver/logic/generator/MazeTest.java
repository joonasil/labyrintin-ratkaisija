/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

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
public class MazeTest {
    
    Maze maze;
    public MazeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        maze = new Maze(5,5,1337);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testConstructor() {
        assertEquals(5,maze.getX());
    }
    
    @Test
    public void testConstructor2() {
        assertEquals(5,maze.getY());
    }
    
    @Test
    public void testChangeDatatype() {
        assertEquals(1,maze.getMaze()[1][1]);
    }
    
    @Test
    public void testChangeDatatype2() {
        assertEquals(1,maze.getMaze()[5][1]);
    }
    
    @Test
    public void testChangeDatatype3() {
        assertEquals(0,maze.getMaze()[0][1]);
    }
    
    @Test
    public void testChangeDatatype4() {
        assertEquals(0,maze.getMaze()[1][0]);
    }
    
    @Test
    public void testChangeDatatype5() {
        assertEquals(1,maze.getMaze()[1][2]);
    }
    
    @Test
    public void testChangeDatatype6() {
        assertEquals(0,maze.getMaze()[2][1]);
    }
}
