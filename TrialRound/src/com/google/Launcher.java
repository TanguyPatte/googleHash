package com.google;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		 
		try {
 
			String sCurrentLine;
			boolean image[][] ;
 
			br = new BufferedReader(new FileReader("doodle.txt"));
			String firstLine = sCurrentLine = br.readLine();
			String dimension[] = firstLine.split(" ");

			int nbLines =  Integer.parseInt(dimension[0]);
			int nbColumns =  Integer.parseInt(dimension[1]);
			image = new boolean[nbLines][nbColumns];
			int lineIterator = 0;
			while ((sCurrentLine = br.readLine()) != null) {
			//	System.out.println(sCurrentLine);
				image[lineIterator] = new boolean[nbColumns];
				for(int i = 0 ; i< nbColumns ; i++)
				{

					image[lineIterator][i] = sCurrentLine.charAt(i) == '#' ?	 false :true; 					
				}
				lineIterator ++;
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
