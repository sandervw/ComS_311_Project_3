/**
 * @author Sander VanWilligen
 * @author Zackery Lovisa
 */

import java.util.*;


/**
 * 
 * This class contains dynamic programming methods for computing the minimum cost vertical cut
 * of a matrix, and for computing the minimum cost alignment given two strings
 *
 */
public class DynamicProgramming {
	
	/**
	 * This method computes a minimum cost vertical cut of a matrix of integers
	 * Given an n rows m columns matrix, it returns a list of size 2n
	 * The elements of the list are of the form [x0, y0, x1, y1, ... xi, yi]
	 * xi is the row index, and yi is the column index
	 * @param M the array of integers for which the min cost cut is being computed
	 * @return returns an arraylist containing the sequence of 2n indices of the cut
	 */
	public ArrayList<Integer> minCostVC(int[][] M){
		
		int numCol = M[0].length;
		int numRow = M.length;
		
		ArrayList<Integer> results = new ArrayList<Integer>(); //the minimum cut to be returned
		ParentPoint[][] costMatrix = new ParentPoint[numRow][numCol]; //matrix of costs
		
		//This loop creates a matrix of costs
		//it takes O(mn) time
		for(int i=numRow-1; i>=0; i++){ //for each row, starting from the top
			if(i==numRow-1){ //if it is the last row of the matrix, do direct copy of data
				for(int j=0; j<numCol; j++){ //for each column
					costMatrix[i][j] = new ParentPoint(i, j, M[i][j]);
				}
			}
			else{ //not the last row, so need to find min of 3 rows
				for(int j=0; j<numCol; j++){ //for each column
					if(j == 0){ //first column, so only do 2
						if(M[i+1][j] <= M[i+1][j+1]) costMatrix[i][j] = new ParentPoint(i+1, j, M[i+1][j]);
						else costMatrix[i][j] = new ParentPoint(i+1, j+1, M[i+1][j+1]);
					}
					else if(j == numCol-1){ //last column, only do 2
						if(M[i+1][j-1] <= M[i+1][j]) costMatrix[i][j] = new ParentPoint(i+1, j-1, M[i+1][j-1]);
						else costMatrix[i][j] = new ParentPoint(i+1, j, M[i+1][j]);
					}
					else{ //check for min of all 3
						if(M[i+1][j-1] <= M[i+1][j] && M[i+1][j-1] <= M[i+1][j+1]) costMatrix[i][j] = new ParentPoint(i+1, j-1, M[i+1][j-1]);
						else if(M[i+1][j] <= M[i+1][j-1] && M[i+1][j] <= M[i+1][j+1]) costMatrix[i][j] = new ParentPoint(i+1, j, M[i+1][j]);
						else costMatrix[i][j] = new ParentPoint(i+1, j+1, M[i+1][j+1]);
					}
				}
			}
		}
		
		//This loop checks every column to determine which has the lowest cost
		//it takes O(m) time
		int lowestCostCol = 0;
		int lowestCost = Integer.MAX_VALUE;
		//for every column in the matrix (IE, run M times)
		for(int i=0; i<numCol; i++){
			if(costMatrix[0][i].getCost() < lowestCost){
				lowestCost = costMatrix[0][i].getCost();
				lowestCostCol = i;
			}
		}
		
		//This loop creates the list of 2n results
		//it takes O(n) time
		//once the lowest cost has been found, build the int array;
		int rowIndex = 0;
		int colIndex = lowestCostCol;
		results.add(0);
		results.add(colIndex);
		for(int i = 0; i < numRow-1; i++){
			rowIndex = costMatrix[rowIndex][colIndex].getX();
			colIndex = costMatrix[rowIndex][colIndex].getY();
			results.add(rowIndex);
			results.add(colIndex);
		}
		
		//Total time for the algorithm is O(mn + m + n) or O(mn)
		return results;
		
	}
	
	/**
	 * This method finds a string such that the alignment cost is minimized
	 * IE. AlignCost(x; z) =< AlignCost(x; z') over all possible z' (obtained by inserting n - m many $'s in y)
	 * x is at least the length of y
	 * neither of x or y has the character $
	 * length of the returned string z must equal the length of x
	 * @param x a string of length n
	 * @param y a string of length m
	 * @return returns a string z (obtained by inserting $ at n - m indices in y)
	 */
	public String stringAlignment(String x, String y){
		
		//TODO see text example of edit distance dynamic programming
		
		String result = "";
		return result;
	}

}

class ParentPoint{
	
	private int x;
	private int y;
	private int cost;
	
	public ParentPoint(int x, int y, int cost){
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	
	public int getCost(){
		return cost;
	}
	
}
