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
        Random rand = new Random(1337);
        maze = new Maze(5,5,rand);
        result = Solver.breadthFirst(maze);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testBreadthFirst() {
        assertEquals(11,result[1][1]);
    }
    
    @Test
    public void testBreadthFirst2() {
        assertEquals(3,result[6][1]);
    }
    
    @Test
    public void testBreadthFirst3() {
        assertEquals(11,result[5][4]);
    }
    
    @Test
    public void testBreadthFirst4() {
        assertEquals(1,result[1][3]);
    }
}
