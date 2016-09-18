/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.joonasil.mazesolver.logic.solver;

import fi.joonasil.mazesolver.logic.generator.Maze;
import java.util.Queue;

/**
 *
 * @author Joonas
 */
public class BreadthFirst {
    private Maze maze;
    private int color[];
    private int distance[];
    private Queue queue;
}
