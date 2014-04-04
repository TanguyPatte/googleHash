package trialcode;

import java.awt.Point;
import java.util.LinkedList;

public class Sharpening {
	public static LinkedList<Point> fillToBlank;
	
	/**
	 * We assume dimension is pair
	 * @param picture
	 * @return
	 */
	public static boolean[][] sharpen(boolean[][] picture){
		fillToBlank=new LinkedList<Point>();
		
		int dim_Y=picture.length;
		int dim_X=picture[0].length;
		
		boolean[][] output=picture.clone();
		for (int i=0;i<dim_X-1; i++){
			for(int j=0;j<dim_Y-1; j++){
				if (picture[j][i] && picture[j+1][i] && picture[j][i+1] && !picture[j+1][i+1] && !output[j+1][i+1]){
					output[j+1][i+1]=true;
					fillToBlank.add(new Point(j+1,i+1));
				}
				if (picture[j][i] && picture[j+1][i] && !picture[j][i+1] && picture[j+1][i+1] && !output[j][i+1]){
					output[j][i+1]=true;
					fillToBlank.add(new Point(j,i+1));
				}
				if (picture[j][i] && !picture[j+1][i] && picture[j][i+1] && picture[j+1][i+1] && !output[j+1][i]){
					output[j+1][i]=true;
					fillToBlank.add(new Point(j+1,i));
				}
				if (!picture[j][i] && picture[j+1][i] && picture[j][i+1] && picture[j+1][i+1] && !output[j][i]){
					output[j][i]=true;
					fillToBlank.add(new Point(j,i));
				}				
			}
		}
		return output;
	}
}
