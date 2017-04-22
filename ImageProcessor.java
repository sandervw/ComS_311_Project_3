import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Sander VanWilligen
 * @author Zackery Lovisa
 */

/**
 * 
 * This class uses the algorithm described in the text and our 
 * DynamicProgramming methods to reduce the width of an image
 *
 */
public class ImageProcessor {
	
	private String imageFile;
	private int[][]importance;
	Picture picture;
	
	/**
	 * constructor for the ImageProcessor class
	 * @param imageFile stores the name of the file we are creating the class from
	 */
	public ImageProcessor(String imageFile){
		
		this.imageFile = imageFile;
		this.picture = new Picture(imageFile);
		
		
	}
	
	/**
	 * computes and returns a new picture whose width is ceiling(x*W)
	 * Uses algorithm described in text to reduce image width
	 * uses dynamic programming methods from DynamicProgramming
	 * @param x the multiplier for the new image size. IE, will be a value such that 0 <= x <= 1
	 * @return A new picture whose size is ceiling(W*x)
	 * 
	 */
	public Picture reduceWidth(double x){
		
		
		Picture result = new Picture(picture);
		int newWidth = (int)Math.ceil(picture.width()*x);
		
		int difference = picture.width() - newWidth;
		ArrayList<Integer> cutValues;
		for(int k=0; k<difference; k++){
			
			this.importance = new int[result.height()][result.width()];
			int yImportance;
			int xImportance;
			Color p1;
			Color p2;
			
			for(int i=0; i<result.height(); i++){
				for(int j=0; j<result.width(); j++){
					
					if(i == 0){
						p1 = result.get(j, result.height()-1);
						p2 = result.get(j, i+1);
						yImportance = this.dist(p1.getRed(), p2.getRed(), p1.getBlue(), p2.getBlue(), p1.getGreen(), p2.getGreen());
					}
					else if(i == result.height()-1){
						p1 = result.get(j, i-1);
						p2 = result.get(j, 0);
						yImportance = this.dist(p1.getRed(), p2.getRed(), p1.getBlue(), p2.getBlue(), p1.getGreen(), p2.getGreen());
					}
					else{
						p1 = result.get(j, i-1);
						p2 = result.get(j, i+1);
						yImportance = this.dist(p1.getRed(), p2.getRed(), p1.getBlue(), p2.getBlue(), p1.getGreen(), p2.getGreen());
					}
					
					if(j == 0){
						p1 = result.get(result.width()-1, i);
						p2 = result.get(j+1, i);
						xImportance = this.dist(p1.getRed(), p2.getRed(), p1.getBlue(), p2.getBlue(), p1.getGreen(), p2.getGreen());
					}
					else if(j == result.width()-1){
						p1 = result.get(0, i);
						p2 = result.get(j-1, i);
						xImportance = this.dist(p1.getRed(), p2.getRed(), p1.getBlue(), p2.getBlue(), p1.getGreen(), p2.getGreen());
					}
					else{
						p1 = result.get(j-1, i);
						p2 = result.get(j+1, i);
						xImportance = this.dist(p1.getRed(), p2.getRed(), p1.getBlue(), p2.getBlue(), p1.getGreen(), p2.getGreen());
					}
					
					importance[i][j] = (xImportance+yImportance);
					
				}
			}
			
			cutValues = DynamicProgramming.minCostVC(importance);
			//create the new picture
			Picture tempResult = new Picture(result.width()-1, result.height());
			for(int i=0; i<result.height(); i++){
				for(int j=0; j<result.width()-1; j++){
					if(j < cutValues.get(i*2+1)){
						tempResult.set(j, i, result.get(j, i));
					}
					else if(j > cutValues.get(i*2+1)){
						tempResult.set(j-1, i, result.get(j, i));
					}
				}
			}
			result = new Picture(tempResult);
			result.show();
		}
		return result;
	}
	
	private int dist(int r1, int r2, int g1, int g2, int b1, int b2){
		
		int dr = (r1 - r2)*(r1 - r2);
		int dg = (g1 - g2)*(g1 - g2);
		int db = (b1 - b2)*(b1 - b2);
		return (dr+dg+db);
		
	}

}
