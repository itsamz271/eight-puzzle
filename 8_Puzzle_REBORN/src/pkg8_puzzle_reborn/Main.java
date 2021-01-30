//Abdul Moiz Amir (217210582) | Abdulaziz Ashy (217210628)
package pkg8_puzzle_reborn;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author abdulmoiz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        
        //Manual input used. Not generating search tree.
        
        System.out.println("Enter the initial board [e.g 0 1 2 4 8 7 5 6 3]: ");
        int[] startingStateBoard = dispatchEightPuzzle(input.nextLine().split(" "));
        
        
        System.out.println("Enter 'o' for misplaced tiles heuristic\n"
                + "Enter 'm' for manhattan distance heuristic");
        char heuristic = input.next().charAt(0);
        aStar.search(startingStateBoard, heuristic);
        
        
        
    }
    
    public static int[] dispatchEightPuzzle(String[] a) {
        int[] initState = new int[9];
        for (int i = 0; i < a.length; i++) {
            initState[i] = Integer.parseInt(a[i]);
        }
        return initState;
    }
    
    public static boolean isSolvable(int[] puzzle ) { 
    int invCount = getInvCount(puzzle); 
    return (invCount % 2 == 0); 
    }
    
    public static int[] generateStartingBoard()
    {
        int[] board = new int[9];
        ArrayList<String> values = new ArrayList<>();
        
        for(int i =0; i<9; i++)
        {
            values.add(Integer.toString(i));
        }
        
        
        int index=0;
        
        while(!(values.isEmpty()))
        {
            int rnd = (int) (Math.random() * 9);
            String str = Integer.toString(rnd);
            
            if(values.contains(str))
            {
                board[index]= rnd;
                index++;
                values.remove(str);
            }
        }
        return board;
    }
    
    public static int[] generateSolvablePuzzle(){
        int[] finalBoard = generateStartingBoard();
        while(!isSolvable(finalBoard)){
            
            finalBoard = generateStartingBoard();
        }
        return finalBoard;
    }
           
    
    public static int getInvCount(int[] board) 
    { 
        int[][] arr = new int[3][3];
        
        int index=0;
        for(int row = 0; row<3; row++)
        {
            for(int col = 0; col <3; col++)
            {
                arr[row][col] = board[index];
            }
        }
                
        int inv_count = 0; 
        
        for (int i = 0; i < 3 - 1; i++) 
            for (int j = i + 1; j < 3; j++) 
          
            // Value 0 is used for empty space 
            if (arr[j][i] > 0 && 
                            arr[j][i] > arr[i][j]) 
                inv_count++;
        return inv_count; 
    } 
    
    public static boolean contains(int[] arr, int toCheckValue)
    {
        for (int element : arr) 
        {
            if (element == toCheckValue) 
            {
                return true;
            }
        }
        return false;
    }
    
    
    
}
