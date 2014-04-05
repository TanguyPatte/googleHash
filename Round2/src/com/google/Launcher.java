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
		// Test reading
		Reader.readFile();
		Node[] arrtest=Node.nodes;
		Edge[] edgetest=Edge.edges;
		// create a fake array of vehicles
		List<LinkedList<Node>> fake_solution= new LinkedList<LinkedList<Node>>();
		for (int i=0; i<8; i++){
			LinkedList<Node> test=new LinkedList<Node>();
			test.add(new Node(10, 3,3));
			fake_solution.add(test);
		}
		
		Car[] cars= Car.cars;
		Car.moveCars(1000);
		
		Writing.writeSolution(fake_solution);
	}

}
