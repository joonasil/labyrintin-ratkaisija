/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.solver;

import fi.joonasil.mazesolver.logic.generator.Maze;
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
public class SolverTest {
    Maze maze;
    int[][] result;
    public SolverTest() {
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
        Solver.breadthFirst(maze.getMaze());
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testBreadthFirst() {
        assertEquals(11,maze.getMaze()[1][1]);
    }
    
    @Test
    public void testBreadthFirst2() {
        assertEquals(3,maze.getMaze()[5][1]);
    }
    
    @Test
    public void testBreadthFirst3() {
        assertEquals(11,maze.getMaze()[3][3]);
    }
    
    @Test
    public void testBreadthFirst4() {
        assertEquals(1,maze.getMaze()[7][1]);
    }
}
