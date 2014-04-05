package com.google;

import java.util.LinkedList;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IOSimple.readFile("doodle.txt");
		
		LinkedList<String> b= new LinkedList<String>();
		b.add("blabla");
		b.add("blibli");
		IOSimple.writeFile("coucou2.txt", b);
		IOSimple.writeFile("coucou.txt", "on est les champions");
		System.out.println("Ah que coucou!");
		for (String s : IOSimple.readFile("coucou3.txt")){
			System.out.println(s);
		}
	}

}
