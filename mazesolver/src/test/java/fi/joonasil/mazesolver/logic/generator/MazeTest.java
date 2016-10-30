/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.generator;

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
    
    private Maze maze;
    
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
        maze = new Maze(5,5,(long)1337);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertEquals(25,maze.getX()*maze.getY());
        assertEquals("Prim's",maze.getGenAlg());
        assertEquals(0,maze.getPathLength());
        assertEquals(false,maze.getSolved()[0]);
        assertEquals(0,maze.getTimeAStar());
        assertEquals(0,maze.getTimeBFS());
        assertEquals(0,maze.getTimeIDA());
        int[][] correct = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0}, 
            {0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0}, 
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0}, 
            {0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, 
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0}, 
            {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} 
        };
        for(int i = 0; i < correct.length; i++){
            for(int j = 0; j < correct[0].length; j++){
                assertEquals(true, maze.getMaze()[j][i] == correct[i][j]);
            }
        }
    }
    
    @Test
    public void testConstructor2() {
        maze = new Maze(5,5);
        assertEquals(25,maze.getX()*maze.getY());
        assertEquals("Prim's",maze.getGenAlg());
        assertEquals(0,maze.getPathLength());
        assertEquals(false,maze.getSolved()[0]);
        assertEquals(0,maze.getTimeAStar());
        assertEquals(0,maze.getTimeBFS());
        assertEquals(0,maze.getTimeIDA());
    }
    
    @Test
    public void testConstructor3() {
        maze = new Maze(5,5,(long)1337,0);
        assertEquals(25,maze.getX()*maze.getY());
        assertEquals("Prim's",maze.getGenAlg());
        assertEquals(0,maze.getPathLength());
        assertEquals(false,maze.getSolved()[0]);
        assertEquals(0,maze.getTimeAStar());
        assertEquals(0,maze.getTimeBFS());
        assertEquals(0,maze.getTimeIDA());
        int[][] correct = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0}, 
            {0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0}, 
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0}, 
            {0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, 
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0}, 
            {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} 
        };
        for(int i = 0; i < correct.length; i++){
            for(int j = 0; j < correct[0].length; j++){
                assertEquals(true, maze.getMaze()[j][i] == correct[i][j]);
            }
        }
    }
    
    @Test
    public void testConstructor4() {
        maze = new Maze(5,5,0);
        assertEquals(25,maze.getX()*maze.getY());
        assertEquals("Prim's",maze.getGenAlg());
        assertEquals(0,maze.getPathLength());
        assertEquals(false,maze.getSolved()[0]);
        assertEquals(0,maze.getTimeAStar());
        assertEquals(0,maze.getTimeBFS());
        assertEquals(0,maze.getTimeIDA());
    }
    
    @Test
    public void testSolveBFS() {
        maze.solveBreadthFrist();
        assertEquals(16,maze.getPathLength());
    }
    
    @Test
    public void testSolveAStar() {
        maze.solveAStar();
        assertEquals(16,maze.getPathLength());
    }
    
    @Test
    public void testSolveIDA() {
        maze.solveIDA();
        assertEquals(16,maze.getPathLength());
    }
    
    @Test
    public void testToString() {
        maze = new Maze(2,2,(long)2);
        String s = "0 0 0 0 0 \n0 1 1 1 0 \n0 1 0 0 0 \n0 1 1 1 0 \n0 0 0 0 0 \n";
        assertEquals(true,s.equals(maze.toString())); 
    }
    
    @Test
    public void testToString2() {
        maze = new Maze(2,2,(long)2);
        maze.solveBreadthFrist();
        String s = "0 0 0 0 0 \n0 113 3 0 \n0 110 0 0 \n0 1111110 \n0 0 0 0 0 \n";
        assertEquals(true,s.equals(maze.toString())); 
    }
}
