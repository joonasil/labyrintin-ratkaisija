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
public class GeneratorTest {
    
    int[][] maze;
    
    public GeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGeneratePrim() {
        maze = Generator.generatePrim(new Random(1), 5, 5);
        int[][] correct = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} 
        };
        for(int i = 0; i < correct.length; i++){
            for(int j = 0; j < correct[0].length; j++){
                assertEquals(true, maze[j][i] == correct[i][j]);
            }
        }
    }
    
    @Test
    public void testGenerateDFS() {
        maze = Generator.generateDFS(new Random(1), 5, 5);
        int[][] correct = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0}, 
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0}, 
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0}, 
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0}, 
            {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0}, 
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0}, 
            {0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0}, 
            {0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0}, 
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0} 
        };
        for(int i = 0; i < correct.length; i++){
            for(int j = 0; j < correct[0].length; j++){
                assertEquals(true, maze[j][i] == correct[i][j]);
            }
        }
    }
    
    @Test
    public void testGenerateKruskal() {
        maze = Generator.generateKruskal(new Random(1), 5, 5);
        int[][] correct = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, 
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0}, 
            {0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0}, 
            {0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0}, 
            {0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0}, 
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0}, 
            {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0}, 
            {0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0}, 
            {0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  
        };
        for(int i = 0; i < correct.length; i++){
            for(int j = 0; j < correct[0].length; j++){
                assertEquals(true, maze[j][i] == correct[i][j]);
            }
        }
    }
}
