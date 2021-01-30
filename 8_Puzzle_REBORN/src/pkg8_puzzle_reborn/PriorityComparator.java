/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8_puzzle_reborn;

import java.util.Comparator;

/**
 *
 * @author abdulmoiz
 */
public class PriorityComparator implements Comparator<SearchNode>
{

    @Override
    public int compare(SearchNode o1, SearchNode o2) 
    {
        if(o1.getFCost()>o2.getFCost())
            return 1;
        if(o1.getFCost()<o2.getFCost())
            return -1;
        return 0;
    }
    
}
