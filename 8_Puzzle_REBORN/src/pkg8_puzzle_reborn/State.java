/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8_puzzle_reborn;

import java.util.ArrayList;

/**
 *
 * @author abdulmoiz
 */
public interface State 
{
    // determine if current state is goal
    boolean isGoal();

    // generate successors to the current state
    ArrayList<State> genSuccessors();

    // determine cost from initial state to THIS state
    double findCost();

    // print the current state
    void printState();

    // compare the actual state data
    boolean equals(State s);
}
