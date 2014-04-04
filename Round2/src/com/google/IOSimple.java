package com.google;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

abstract  class IOSimple {
	/**
	 * Return a list of all lines in the file
	 * @param path
	 * @return
	 */
	public static List<String> readFile(String path){
		LinkedList<String> list = null;
		try{
		FileReader fr=new FileReader(path); 
		BufferedReader br=new BufferedReader(fr);
		String ligne;
		list= new LinkedList<String>();
		while ((ligne=br.readLine())!=null){
			if (ligne!="") // does not take into account empty line
				list.add(ligne);
		}
		br.close();
		fr.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	};
	
	/**
	 * write a file with the specified lines
	 * @param path
	 * @param lines
	 */
	public static void writeFile(String fileName, List<String> lines){
		try{
		FileWriter fw=new FileWriter(fileName);
		BufferedWriter bw= new BufferedWriter(fw, 8192);
		for (String s2 : lines){
			bw.write(s2);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		fw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Write just one string
	 * @param path
	 * @param line
	 */
	public static void writeFile(String fileName, String line){
		try{
			FileWriter fw=new FileWriter(fileName);
			BufferedWriter bw= new BufferedWriter(fw, 8192);
			bw.write(line);
			bw.flush();
			bw.close();
			fw.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}
	
}
