import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = null;

		try {
			// Reading
			String sCurrentLine;
			boolean image[][];

			System.out.println("Loading the image...");
			br = new BufferedReader(new FileReader("doodle.txt"));
			String firstLine = sCurrentLine = br.readLine();
			String dimension[] = firstLine.split(" ");

			int nbLines = Integer.parseInt(dimension[0]);
			int nbColumns = Integer.parseInt(dimension[1]);
			image = new boolean[nbLines][nbColumns];
			int lineIterator = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				image[lineIterator] = new boolean[nbColumns];
				for (int i = 0; i < nbColumns; i++) {

					image[lineIterator][i] = sCurrentLine.charAt(i) == '#' ? true : false;
				}
				lineIterator++;
			}

			System.out.println("Sharpening the image...");
			boolean[][] closedImg = new boolean[nbLines][nbColumns];
			ImageProcessing.close(image, closedImg, nbLines, nbColumns, 1);
			writeImage(closedImg, nbLines, nbColumns, "closure.txt");
			
			System.out.println("Computing white dots list...");
			ImageProcessing.computeWhiteDots(image, closedImg, nbLines, nbColumns);

			System.out.println("Computing black squares list...");
			ImageProcessing.computeBlackSquares(closedImg, nbLines, nbColumns);
			
			System.out.println("Pruning black squares list...");
			ImageProcessing.prune(nbLines, nbColumns);

			System.out.println("Permute elements...");
			ImageProcessing.randomPermutation();
			
			System.out.println(ImageProcessing.blackSquares.size() + ImageProcessing.whiteDots.size() + " instructions generated !");

			// Check
			boolean[][] res = ImageProcessing.reconstruct(nbLines, nbColumns);
			int errors = 0;
			for (int i = 0 ; i < nbLines; ++i) {
				for (int j = 0 ; j < nbColumns; ++j) {
					if (res[i][j] != image[i][j])
						++errors;
				}
			}
			writeImage(res, nbLines, nbColumns, "res.txt");
			if (errors > 0)
				System.out.println("Images don't match (" + errors + " errors)...");
			else
				System.out.println("Images match!");

			System.out.println("Writing output...");
			String output = Printer.generateOutput(ImageProcessing.blackSquares, ImageProcessing.whiteDots);
			FileWriter fw=new FileWriter("output.txt");
			BufferedWriter bw= new BufferedWriter(fw, 8192);
			bw.write(output);
			bw.flush();
			bw.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void writeImage(boolean[][] img, int N, int M, String output) throws IOException {
		FileWriter fw=new FileWriter(output);
		BufferedWriter bw= new BufferedWriter(fw, 8192);
		for (int i = 0 ; i < N; ++i) {
			for (int j = 0 ; j < M ; ++j) {
				if (img[i][j])
					bw.write("#");
				else
					bw.write(".");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		fw.close();
	}

}