package com.google;

import java.util.LinkedList;
import java.util.List;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test1();
	}
	
	/**
	 * Test basic writing and reading
	 * 
	 */
	public static void Test1(){
		// create a fake array of vehicles
		List<LinkedList<Node>> fake_solution= new LinkedList<LinkedList<Node>>();
		for (int i=0; i<8; i++){
			LinkedList<Node> test=new LinkedList<Node>();
			test.add(new Node(10, 3,3));
			fake_solution.add(test);
		}
		Writing.writeSolution(fake_solution);
	}

}
