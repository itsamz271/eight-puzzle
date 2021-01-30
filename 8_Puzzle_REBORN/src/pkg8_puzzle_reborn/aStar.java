/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8_puzzle_reborn;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author abdulmoiz
 */
public class aStar
{
 public static void search(int[] board, char heuristic)
 {
     
     EightPuzzleState state = new EightPuzzleState(board);
		SearchNode startNode = new SearchNode(state);
                //ArrayList<SearchNode> visited = new ArrayList<>();

		//Queue<SearchNode> queue = new LinkedList<SearchNode>();
                PriorityQueue<SearchNode> queue = new PriorityQueue<>(5, new PriorityComparator());
		queue.add(startNode);

		int exploredNodes = 1; // counter for explored nodes

		while (!queue.isEmpty()) 
		{
			SearchNode currentNode = queue.poll();

			// if the currentNode is not the goal state
			if (!currentNode.getCurState().isGoal())
			{
				
				// generate currentNode's successors
				ArrayList<State> successors = currentNode.getCurState()
                                                    .genSuccessors();
				//ArrayList<SearchNode> childNodes = new ArrayList<SearchNode>();
                                PriorityQueue<SearchNode> childNodes = new PriorityQueue<SearchNode>(5, new PriorityComparator());

                                //Check if nodes are already explored
				for (State successor: successors)
				{
                                    
					SearchNode childNode;
					// make the node
					if (heuristic == 'o')
					{
						/*
						 * Create a new SearchNode, with currentNode as the parent,
						 * currentNode's cost + the new cost (1) for this state,
						 * and the Out of Place h(n) value
						 */
						childNode = new SearchNode(currentNode, successor, currentNode.getCost() + 1, (((EightPuzzleState)successor).getOutOfPlace()));
					}
					else
					{
						// See previous comment; uses manhattan distance
						childNode = new SearchNode(currentNode,
								successor, currentNode.getCost()
										+ 1,
								((EightPuzzleState) successor)
										.getManDist());
					}

					// Check if child has already been explored
					if (!isVisited(childNode))
					{
						queue.add(childNode);
					}
				}
                                 
                                
                                 
				exploredNodes++;
			}
                        
                        
			else
			{
				// Use a stack to track the path from the starting state to the
				// goal state
				Stack<SearchNode> solutionPath = new Stack<SearchNode>();
				solutionPath.push(currentNode);
				currentNode = currentNode.getParent();

				while (currentNode.getParent() != null)
				{
					solutionPath.push(currentNode);
					currentNode = currentNode.getParent();
				}
				solutionPath.push(currentNode);

				// The size of the stack before looping through and emptying it.
				int loopSize = solutionPath.size();

				for (int i = 0; i < loopSize; i++)
				{
					currentNode = solutionPath.pop();
					currentNode.getCurState().printState();
					System.out.println();
					System.out.println();
				}
				System.out.println("The cost was: " + currentNode.getCost());
                                System.out.println("The explored nodes were: " +exploredNodes);
				System.exit(0);
			}
		}

		
		System.out.println("Invalid Input");

	}

	/*
	 * Helper method to check to see if a SearchNode has already been evaluated.
	 * Returns true if it has, false if it hasn't.
	 */
	private static boolean isVisited(SearchNode n)
	{
		boolean flag = false;
		SearchNode checkNode = n;

		// While n's parent isn't null, check to see if it's equal to the node
		// we're looking for.
		while (n.getParent() != null && !flag)
		{
			if (n.getParent().getCurState().equals(checkNode.getCurState()))
			{
				flag = true;
			}
			n = n.getParent();
		}

		return flag;
	}    
}


