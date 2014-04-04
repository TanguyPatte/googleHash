static class SecondPass {
    public static void process(const boolean[][] array, int N, int M) {
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
                            
                rectangles.add(new Rectangle.Integer(x, y, w, h));
            }
        }
    }
    
    public static boolean checkCol(const boolean[][] array, int N, int M, int x, int y, int w, int h) {
        for (int i = y; i < y+h; ++i) {
            if (!array[i][x+w])
                return false;
        }
        return true;
    }
    
    public static boolean checkRow(const boolean[][] array, int N, int M, int x, int y, int w, int h) {
        for (int j = x; j < x+w; ++j) {
            if (!array[y+h][j])
                return false;
        }
        return true;
    }
}