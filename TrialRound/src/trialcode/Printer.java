package trialcode;

import java.awt.Point;
import java.util.LinkedList;

public class Printer {

		public static void generateOutput(LinkedList<Square> squareList,LinkedList<Point> fillToBlank){
			int nb = squareList.size() + fillToBlank.size();
			String str = "" + nb +"\n";
			for(final Square c : squareList){
				str += getInstructionForSquare(c);
			}
			for(final Point c : fillToBlank){
				str += getInstructionForErase(c);
			}
		}
		
		public static String getInstructionForSquare(Square square){
			return "PAINTSQ " + square.center.x + " " + square.center.y + " " + square.radius +"\n";
		}
		
		public static String getInstructionForErase(Point p){
			return "ERASECELL "+ p.x + " " + p.y + "\n";
		}
}
