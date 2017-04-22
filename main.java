import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		
		//VC test
		int[][] testMatrix = new int[4][4];
		testMatrix[0][0] = 2;
		testMatrix[0][1] = 5;
		testMatrix[0][2] = 9;
		testMatrix[0][3] = 6;
		
		testMatrix[1][0] = 8;
		testMatrix[1][1] = 7;
		testMatrix[1][2] = 1;
		testMatrix[1][3] = 3;
		
		testMatrix[2][0] = 1;
		testMatrix[2][1] = 4;
		testMatrix[2][2] = 9;
		testMatrix[2][3] = 5;
		
		testMatrix[3][0] = 3;
		testMatrix[3][1] = 11;
		testMatrix[3][2] = 2;
		testMatrix[3][3] = 1;
		
		ArrayList<Integer> tempResult = DynamicProgramming.minCostVC(testMatrix);
		System.out.println(tempResult.toString());
		
		//alignment test (works)
		String s1 = "ABCEG";
		String s2 = "";
		String temp;
		temp = DynamicProgramming.stringAlignment(s1, s2);
		System.out.println(temp);
		
		ImageProcessor p = new ImageProcessor("beforeTest.jpg");
		p.reduceWidth(.5);
	}

}
