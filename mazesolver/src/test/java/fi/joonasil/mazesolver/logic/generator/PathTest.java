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
public class PathTest {
    
    Path path;
    public PathTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        path = new Path(3,3,4);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testConstructor() {
        int[] answer = new int[]{0,0,0,0,1,0,0,0,0};
        for(int i = 0; i < answer.length; i++) {
            assertEquals(answer[i], path.getMap()[i]);
        }
    }
    
    @Test
    public void testConstructor2() {
        assertEquals(4,path.getIndex());
    }
    
    @Test
    public void testSetToMaze() {
        path.setToMaze();
        assertEquals(true,path.isPartOfMaze());
    }
    
    @Test
    public void testOpenWall() {
        path.openWall(1);
        assertEquals(1,path.getMap()[1]);
    }
    
    @Test
    public void testOpenWall2() {
        path.openWall(3);
        assertEquals(1,path.getMap()[3]);
    }
    
    @Test
    public void testOpenWall3() {
        path.openWall(5);
        assertEquals(1,path.getMap()[5]);
    }
    
    @Test
    public void testOpenWall4() {
        path.openWall(7);
        assertEquals(1,path.getMap()[7]);
    }
    
    @Test
    public void testOpenWall5() {
        path.openWall(1);
        path.openWall(3);
        path.openWall(5);
        path.openWall(7);
        assertEquals(true,path.getWalls().get(0).isOpen());
    }
    
    @Test
    public void testWalls() {
        assertEquals(4,path.getWalls().size());
    }
}
