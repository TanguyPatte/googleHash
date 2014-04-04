package trialcode;

import Rectangle;

static class SecondPass {
	public static List<Rectangle> process(boolean[][] array, int N, int M) {
        List<Rectangle> rectangles = new LinkedList<Rectangle>();
        int x, y, w, h;
        boolean col = true, valid = true;
        
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if ((i > 0 && array[i-1][j]) || (j > 0 && array[i][j-1])) // it's not a new rectangle
                  continue;
                
                x = j;
                y = i;
                w = h = 1;
                col = valid = true;
                while (valid && (x+w < M || y+h < N)) {
                    if (col && x+w < M)
                        if ((valid = checkCol(array, N, M, x, y, w, h))
                            ++w;
                    else if (y+h < N)
                        if ((valid = checkRow(array, N, M, x, y, w, h))
                            ++h;
                    col = !col;
                }
                            
                Rectangle r = new Rectangle();
                r.x = x;
                r.y = y;
                r.width = w;
                r.height = h;
                rectangles.add(r);
            }
        }
                            
        return rectangles;
    }

	public static boolean checkCol(boolean[][] array, int N, int M, int x, int y, int w, int h) {
		for (int i = y; i < y + h; ++i) {
			if (!array[i][x + w])
				return false;
		}
		return true;
	}

	public static boolean checkRow(boolean[][] array, int N, int M, int x, int y, int w, int h) {
		for (int j = x; j < x + w; ++j) {
			if (!array[y + h][j])
				return false;
		}
		return true;
	}

	class RectangleComparator implements Comparator<Rectangle> {
		public int compare(Rectangle r1, Rectangle r2) {
			int s1 = Math.min(r1.width, r1.height);
			int s2 = Math.min(r2.width, r2.height);
			if (s1 < s2)
				return -1;
			if (s1 == s2)
				return 0;
			return 1;
		}
	}

	public static List<Rectangle> sortRectangles(List<Rectangle> rectangles) {
                            Collection<Rectangle>.sort(rectangles, new RectangleComparator());
                        }
}