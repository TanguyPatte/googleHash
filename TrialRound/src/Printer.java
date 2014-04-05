import java.awt.Point;
import java.util.List;

public class Printer {

		public static String generateOutput(List<ImageProcessing.Square> squares, List<Point> fillToBlank){
			int nb = squares.size() + fillToBlank.size();
			String str = "" + nb +"\n";
			for(final ImageProcessing.Square c : squares){
				str += getInstructionForSquare(c);
			}
			for(final Point c : fillToBlank){
				str += getInstructionForErase(c);
			}
			
			return str;
			
		}
		
		public static String getInstructionForSquare(ImageProcessing.Square square){
			return "PAINTSQ " + square.center.x + " " + square.center.y + " " + square.S +"\n";
		}
		
		public static String getInstructionForErase(Point p){
			return "ERASECELL "+ p.x + " " + p.y + "\n";
		}
}
