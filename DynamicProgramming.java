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
		ArrayList<Integer> results = null;
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
		String result = "";
		return result;
	}

}
