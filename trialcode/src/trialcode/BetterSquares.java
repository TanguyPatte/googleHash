package trialcode;

import Square;

public static class BetterSquares {
public static List<Square> (boolean[][] array, int N, int M, List<Square> squares) {
	List<Square> betterSquare = new LinkedList<Square>();
	boolean[][] buffer = new boolean[N][M];
	
	for (int i = 0; i < N ; ++i)
		for (int j = 0 ; j < M; ++j)
			buffer[i][j] = array[i][j];
	
	for(Square square : squares) {
		if (checkSquare(buffer, square)) {
			betterSquare.add(square);
			clearSquare(buffer, square);
		}
			
	}
}

public static boolean checkSquare(boolean[][] buffer, Square square) {
	for (int i = square.center.y; i < square.center.y + square.radius; ++i)
		for (int j = square.center.x; j < square.center.x+square.radius; ++j)
			if (buffer[i][j])
				return true;
	return false;
}

public static void clearSquare(boolean[][] buffer, Square square) {
	for (int i = square.center.y; i < square.center.y + square.radius; ++i)
		for (int j = square.center.x; j < square.center.x+square.radius; ++j)
			buffer[i][j] = false;
}

}
