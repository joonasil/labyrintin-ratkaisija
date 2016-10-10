/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver;


import fi.joonasil.mazesolver.gui.Screen;
import fi.joonasil.mazesolver.logic.generator.Maze;


public class Mazesolver {
    
    private static Maze maze;
    private static Screen screen;
    
    public static Maze getMaze() {
        if(Mazesolver.maze == null)
            Mazesolver.maze = new Maze(10,10);
        return Mazesolver.maze;
    }
    
    public static Screen getScreen() {
        if(Mazesolver.screen == null)
            Mazesolver.screen = new Screen();
        return Mazesolver.screen;
    }
    
    public static void setMaze(Maze maze) {
        Mazesolver.maze = maze;
    }
    
    public static void setScreen(Screen screen) {
        Mazesolver.screen = screen;
    }
    
}
