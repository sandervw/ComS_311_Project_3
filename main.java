
public class main {

	public static void main(String[] args) {
		
		String s1 = "ABCEG";
		String s2 = "AQCG";
		int temp = 7;
		DynamicProgramming d = new DynamicProgramming();
		temp = d.stringAlignment(s1, s2);
		System.out.println(temp);

	}

}
