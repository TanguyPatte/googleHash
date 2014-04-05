package com.google;

import java.util.LinkedList;
import java.util.List;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test3();
	}


	public static void Test3(){
		Reader.readFile();
		Simulation s= new Simulation();
		int step=1000;
		for (int t=0; t<Reader.time; t+=step){
			Simulation s2=s.clone(1).get(0);
			s2.launcheSimulation(t);
			s=s2;
			System.out.println(t);
		}
		List<LinkedList<Node>> solution= new LinkedList<LinkedList<Node>>();
		for (int i=0; i<Car.cars.length; i++){
			LinkedList<Node> path_car= new LinkedList<Node>();
			while (!s.events.isEmpty()) {
				Car car =  s.events.poll();
				for (Integer j : car.history)
					path_car.add(Node.nodes[j]);
			}
			solution.add(path_car);
		}
		Writing.writeSolution(solution);
		System.out.println(s.score);
	}
	/**
	/**
	 * Test basic writing and reading
	 * 

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

	/**
	 * Test basic writing and reading
	 * 

	public static void Test2(){
		// Test reading
		Reader.readFile();
		System.out.println("coucou");
		System.out.println(Reader.time);
		Car.moveCars(Reader.time);
		List<LinkedList<Node>> solution= new LinkedList<LinkedList<Node>>();
		for (int i=0; i<Car.cars.length; i++){
			LinkedList<Node> path_car= new LinkedList<Node>();
			for (Integer j : Car.cars[i].history){
				path_car.add(Node.nodes[j]);
			}
			solution.add(path_car);
		}
		Writing.writeSolution(solution);
		System.out.println(Car.computeScore());
	}
	 */


}
