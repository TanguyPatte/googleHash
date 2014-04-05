package com.google;

import java.util.LinkedList;
import java.util.List;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> l = IOSimple.readFile("paris_54000.txt");
		System.out.println(l.get(0));
		String p[] = l.get(0).split(" ");
		int initValues[] = new int[p.length];
		int nbNode = Integer.parseInt(p[0]) ;
		int nbEdge = Integer.parseInt(p[1]) ;
		int time = Integer.parseInt(p[2]) ;
		int nbCars = Integer.parseInt(p[3]) ;
		int firstNode = Integer.parseInt(p[4]) ;
		int index = 1;
		Node.nodes = new Node[nbNode];
		
		while(index < nbNode){
			String tmp[] = l.get(index).split(" ");
			
		}
		
		
	}

}
