import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


public class ImageProcessing {
	public static class Square {
		Point center;
		int S;

		public Square(int i, int j, int s) {
			center = new Point(i, j);
			S = s;
		}
	}

	public static boolean OUT_VALUE = false;
	public static List<Point> whiteDots = new LinkedList<Point>();
	public static List<Square> blackSquares = new LinkedList<Square>();

	public static int erode(boolean[][] src, boolean[][] dst, int N, int M) {
		boolean[][] buffer = new boolean[N][M];
		int res = 0;

		for (int i = 0 ; i < N; ++i) 
			for (int j = 0; j < M; ++j) 
				buffer[i][j] = ((i > 0)?src[i-1][j]:OUT_VALUE) && src[i][j] && ((i < N-1)?src[i+1][j]:OUT_VALUE);

		for (int i = 0 ; i < N; ++i) 
			for (int j = 0; j < M; ++j) {
				dst[i][j] = ((j > 0)?buffer[i][j-1]:OUT_VALUE) && buffer[i][j] && ((j < M-1)?buffer[i][j+1]:OUT_VALUE);
				if (dst[i][j])
					++res;
			}

		return res;
	}

	public static int dilate(boolean[][] src, boolean[][] dst, int N, int M) {
		boolean[][] buffer = new boolean[N][M];
		int res = 0;

		for (int i = 0 ; i < N; ++i) 
			for (int j = 0; j < M; ++j) 
				buffer[i][j] = ((i > 0)?src[i-1][j]:OUT_VALUE) || src[i][j] || ((i < N-1)?src[i+1][j]:OUT_VALUE);

		for (int i = 0 ; i < N; ++i) 
			for (int j = 0; j < M; ++j) {
				dst[i][j] = ((j > 0)?buffer[i][j-1]:OUT_VALUE) || buffer[i][j] || ((j < M-1)?buffer[i][j+1]:OUT_VALUE);
				if (dst[i][j])
					++res;
			}

		return res;
	}

	public static void close(boolean[][] src, boolean[][] dst, int N, int M, int kernelSize) {
		boolean[][] buf1 = new boolean[N][M], buf2 = new boolean[N][M], buf3;

		for (int i = 0 ; i < N; ++i) 
			for (int j = 0; j < M; ++j) 
				buf1[i][j] = src[i][j];
		//*
		for (int k = 0; k < kernelSize; ++k) {
			for (int i = 0 ; i < N; ++i) 
				for (int j = 0; j < M; ++j) {
					if (buf1[i][j])
						buf2[i][j] = true;
					else {
						// a  b  c
						// d  *  e
						// f  g  h
						boolean a = (i > 0   && j > 0  )?buf1[i-1][j-1]:OUT_VALUE;
						boolean b = (i > 0             )?buf1[i-1][j  ]:OUT_VALUE;
						boolean c = (i > 0   && j < M-1)?buf1[i-1][j+1]:OUT_VALUE;
						boolean d = (	        j > 0  )?buf1[i  ][j-1]:OUT_VALUE;
						boolean e = (		    j < M-1)?buf1[i  ][j+1]:OUT_VALUE;
						boolean f = (i < N-1 && j > 0  )?buf1[i+1][j-1]:OUT_VALUE;
						boolean g = (i < N-1           )?buf1[i+1][j  ]:OUT_VALUE;
						boolean h = (i < N-1 && j < M-1)?buf1[i+1][j+1]:OUT_VALUE;

						buf2[i][j] = (d && a && b) || (b && c && e) || (e && h && g) || (g && f && d);
					}
				}

			buf3 = buf1;
			buf1 = buf2;
			buf2 = buf3;
		}

		/*/
		for (int k = 0; k < kernelSize; ++k) {
			dilate(buf1, buf2, N, M);
			buf3 = buf1;
			buf1 = buf2;
			buf2 = buf3;
		}

		for (int k = 0; k < kernelSize; ++k) {
			erode(buf1, buf2, N, M);
			buf3 = buf1;
			buf1 = buf2;
			buf2 = buf3;
		}
		/*/

		for (int i = 0 ; i < N; ++i) 
			for (int j = 0; j < M; ++j) 
				dst[i][j] = buf1[i][j];
	}

	public static void computeWhiteDots(boolean[][] img, boolean[][] closedImg, int N, int M) {
		for (int i = 0 ; i < N; ++i) 
			for (int j = 0; j < M; ++j) 
				if (closedImg[i][j] && !img[i][j])
					whiteDots.add(new Point(i, j));
	}

	public static void computeBlackSquares(boolean[][] image, int N, int M) {
		List<boolean[][]> images = new LinkedList<boolean[][]>();
		images.add(image);
		boolean[][] buf1, buf2 = image, buf3;

		for(;;) {
			buf1 = buf2;
			buf2 = new boolean[N][M];
			if (erode(buf1, buf2, N, M) > 0)
				images.add(buf2);
			else 
				break;
		}

		buf1 = new boolean[N][M];
		buf2 = new boolean[N][M];

		for (int i = 0 ; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				buf1[i][j] = buf2[i][j] = false;
			}
		}

		for (int k = images.size()-1; k >= 0; --k) {
			boolean[][] img = images.get(k);

			for (int i = 0 ; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (img[i][j] && !buf1[i][j]) {
						blackSquares.add(new Square(i, j, k));
						buf1[i][j] = true;
					}
				}
			}

			dilate(buf1, buf2, N, M);

			buf3 = buf1;
			buf1 = buf2;
			buf2 = buf3;
		}
	}

	public static void prune(int N, int M) {
		int[][] score = new int[N][M];
		int[][] erasedIdx = new int[N][M];
		List<Point> removedPoints = new LinkedList<Point>();

		for (int i = 0 ; i < N; ++i) 
			for (int j = 0; j < M; ++j) {
				score[i][j] = 0;
				erasedIdx[i][j] = -1;
			}

		for (Square s : blackSquares) {
			for (int i = s.center.x-s.S; i <= s.center.x+s.S; ++i) 
				for (int j = s.center.y-s.S; j <= s.center.y+s.S; ++j) 
					++score[i][j];
		}

		for (int k = 0; k < whiteDots.size(); ++k) {
			Point pt = whiteDots.get(k);
			erasedIdx[pt.x][pt.y] = k;
		}

		for (int k = 0 ; k < blackSquares.size(); ++k) {
			Square s = blackSquares.get(k);
			if (canBeRemoved(score, N, M, s)) {
				for (int i = s.center.x-s.S; i <= s.center.x+s.S; ++i) 
					for (int j = s.center.y-s.S; j <= s.center.y+s.S; ++j) 
						--score[i][j];
				blackSquares.remove(k);
				--k;
			}
		}

		for (int k = 0 ; k < blackSquares.size(); ++k) {
			Square s = blackSquares.get(k);
			int gain = 1;
			int loss = 0;

			for (int i = s.center.x-s.S; i <= s.center.x+s.S; ++i) 
				for (int j = s.center.y-s.S; j <= s.center.y+s.S; ++j) {
					if (score[i][j] == 1) {
						if (erasedIdx[i][j] != -1)
							++gain;
						else
							++loss;
					}
				}

			if (gain > loss) {
				for (int i = s.center.x-s.S; i <= s.center.x+s.S; ++i) 
					for (int j = s.center.y-s.S; j <= s.center.y+s.S; ++j) {
						if (score[i][j] == 1) {
							if (erasedIdx[i][j] != -1) {
								removedPoints.add(whiteDots.get(erasedIdx[i][j]));
								erasedIdx[i][j] = -1;
							} else {
								blackSquares.add(new Square(i, j, 0));
								++score[i][j];
							}
						}
						--score[i][j];
					}

				blackSquares.remove(k);
				--k;
			}
		}

		whiteDots.removeAll(removedPoints);
	}

	public static void randomPermutation() {
		for (int k = 0 ; k < blackSquares.size(); ++k) {
			int kp = (k + (int) (Math.random()*blackSquares.size())) % blackSquares.size();
			Square tmp = blackSquares.get(k);
			blackSquares.set(k, blackSquares.get(kp));
			blackSquares.set(kp, tmp);
		}

		for (int k = 0 ; k < whiteDots.size(); ++k) {
			int kp = (k + (int) (Math.random()*whiteDots.size())) % whiteDots.size();
			Point tmp = whiteDots.get(k);
			whiteDots.set(k, whiteDots.get(kp));
			whiteDots.set(kp, tmp);
		}
	}

	public static boolean[][] reconstruct(int N, int M) {
		boolean[][] res = new boolean[N][M];

		for (int i = 0 ; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				res[i][j] = false;
			}
		}

		for (Square s : blackSquares) {
			for (int i = s.center.x-s.S; i <= s.center.x+s.S; ++i) 
				for (int j = s.center.y-s.S; j <= s.center.y+s.S; ++j) 
					res[i][j] = true;
		}

		for (Point pt : whiteDots) 
			res[pt.x][pt.y] = false;

		return res;
	}

	private static boolean canBeRemoved(int[][] score, int N, int M, Square s) {
		for (int i = s.center.x-s.S; i <= s.center.x+s.S; ++i) 
			for (int j = s.center.y-s.S; j <= s.center.y+s.S; ++j) 
				if (score[i][j] == 1)
					return false;
		return true;
	}
}
