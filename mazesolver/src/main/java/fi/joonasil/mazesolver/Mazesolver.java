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
    
    public Maze getMaze() {
        if(this.maze == null)
            this.maze = new Maze();
    }
    
   
    
    
}
