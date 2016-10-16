/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

import fi.joonasil.mazesolver.Mazesolver;
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
        Mazesolver.setMaze(new Maze(5,5,1337));
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testConstructor() {
        assertEquals(5,Mazesolver.getMaze().getX());
    }
    
    @Test
    public void testConstructor2() {
        assertEquals(5,Mazesolver.getMaze().getY());
    }
    
    @Test
    public void testConstructor3() {
        assertEquals(1,Mazesolver.getMaze().getMaze()[1][1]);
    }
    
    @Test
    public void testConstructor4() {
        assertEquals(1,Mazesolver.getMaze().getMaze()[5][1]);
    }
    
    @Test
    public void testConstructor5() {
        assertEquals(0,Mazesolver.getMaze().getMaze()[0][1]);
    }
    
    @Test
    public void testConstructor6() {
        assertEquals(0,Mazesolver.getMaze().getMaze()[1][0]);
    }
    
    @Test
    public void testConstructor7() {
        assertEquals(1,Mazesolver.getMaze().getMaze()[1][2]);
    }
    
    @Test
    public void testConstructor8() {
        assertEquals(0,Mazesolver.getMaze().getMaze()[2][1]);
    }
    
    @Test
    public void testSolveBFS() {
        Mazesolver.getMaze().solveBreadthFrist();
        assertEquals(16,Mazesolver.getMaze().getPathLength());
    }
    
    @Test
    public void testSolveAStar() {
        Mazesolver.getMaze().solveAStar();
        assertEquals(16,Mazesolver.getMaze().getPathLength());
    }
    
    @Test
    public void testSolveIDA() {
        Mazesolver.getMaze().solveIDA();
        assertEquals(16,Mazesolver.getMaze().getPathLength());
    }
}
