/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.solver;

import fi.joonasil.mazesolver.logic.generator.Maze;
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testBreadthFirst() {
        Solver.breadthFirst(maze);
        assertEquals(11,maze.getMaze()[1][1]);
    }
    
    @Test
    public void testBreadthFirst2() {
        Solver.breadthFirst(maze);
        assertEquals(3,maze.getMaze()[5][1]);
    }
    
    @Test
    public void testBreadthFirst3() {
        Solver.breadthFirst(maze);
        assertEquals(11,maze.getMaze()[3][3]);
    }
    
    @Test
    public void testBreadthFirst4() {
        Solver.breadthFirst(maze);
        assertEquals(1,maze.getMaze()[7][8]);
    }
    
    @Test
    public void testBreadthFirst5() {
        Solver.breadthFirst(maze);
        assertEquals(16,maze.getPathLength());
    }
    
    @Test
    public void testAstar() {
        Solver.aStar(maze);
        assertEquals(11,maze.getMaze()[1][1]);
    }
    
    @Test
    public void testAstar2() {
        Solver.aStar(maze);
        assertEquals(4,maze.getMaze()[3][2]);
    }
    
    @Test
    public void testAstar3() {
        Solver.aStar(maze);
        assertEquals(11,maze.getMaze()[3][3]);
    }
    
    @Test
    public void testAstar4() {
        Solver.aStar(maze);
        assertEquals(1,maze.getMaze()[7][8]);
    }
    
    @Test
    public void testAstar5() {
        Solver.aStar(maze);
        assertEquals(16,maze.getPathLength());
    }
    
    @Test
    public void testIDA() {
        Solver.IDA(maze);
        assertEquals(11,maze.getMaze()[1][1]);
    }
    
    @Test
    public void testIDA2() {
        Solver.IDA(maze);
        assertEquals(11,maze.getMaze()[3][3]);
    }
    
    @Test
    public void testIDA3() {
        Solver.IDA(maze);
        assertEquals(1,maze.getMaze()[7][8]);
    }
    
    @Test
    public void testIDA4() {
        Solver.IDA(maze);
        assertEquals(16,maze.getPathLength());
    }
}
