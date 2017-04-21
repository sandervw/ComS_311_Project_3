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
		for(int i=numRow-1; i>=0; i--){ //for each row, starting from the top
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
	public int stringAlignment(String x, String y){

		/*
		 * recursive algorithm:
		 * int n = x.length
		 * int m = y.length
		 * alignCost(x, y, n, m){
		 * 		if(m == 0) return (n-m)*4;
		 * 		if(n == 0) return -(m-n) * 4
		 * 		if(x[n-1] = y[m-1] return alignCost(x, y, n-1, m-1)); //letters match, so no cost
		 * 		else{ //letters don't match, can do 1 of 2 operations
		 * 			return minimum of:
		 * 				(alignCost(x, y, n-1, m-1) + 2); //shift both indices back 1, essentially saying the letters will be compared
		 * 					//and thus will not match and return a cost of +2
		 * 				(alignCost(x, y, n-1, m) + 4); //shift only the bottom index back 1
		 * 					//this will essentually put and extra $ on the string
		 * 		}
		 * }
		 */
		
		String result = "";
		
		int n = x.length();
		int m = y.length();
		
		//matrix to store costs of alignment
		int costMatrix[][] = new int[n+1][m+1];
		
		//fill matrix in bottom-up manner
		for(int i=0; i <= n; i++){ //for every row
			for (int j=0; j <= m; j++){ //for every column
				
				//the end of the x string has been reached, so need to undo m-n $ cost
				if(i == 0) costMatrix[i][j] = 0;
				
				//the end of the y string has been reached, so need to insert n-m $ symbols
				else if(j == 0) costMatrix[i][j] = ((i-j) * 4);
				
				//characters are the same, so cost does not increase
				else if (x.charAt(i-1) == y.charAt(j-1)) costMatrix[i][j] = costMatrix[i-1][j-1];
				
				//otherwise, the cost is the minimum of equating both symbols or inserting a $
				else{
					//costs less to equate the two than to insert a $ sign
					if((costMatrix[i-1][j-1] + 2) <= costMatrix[i-1][j] + 4) costMatrix[i][j] = (costMatrix[i-1][j-1] + 2);
					//costs less to insert a $ than to equate the two
					else costMatrix[i][j] = (costMatrix[i-1][j] + 4);
				}
				
			}
		}
		
		return costMatrix[n][m];
	}

}

/*
 * This is a class to store the cost of a point, along with the point where that cost came from
 * IE. if the point is at (i, j), and (i+1, j-1) has the lowest cost of its 3, the x and y values of
 *   this object will be i+1 and j-1 respectively
 */
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
