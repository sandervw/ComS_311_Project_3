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
	
	/**
	 * constructor for the ImageProcessor class
	 * @param imageFile stores the name of the file we are creating the class from
	 */
	public ImageProcessor(String imageFile){
		
		this.imageFile = imageFile;
		
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
		Picture result = null;
		return result;
	}

}
