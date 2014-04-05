package com.google;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test3();
	}


	public static void Test4(){
		Reader.readFile();
		int step=54000;
		Simulation s= new Simulation();
		s.launcheSimulation(0, step);
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
	public static void Test3(){
		Reader.readFile();
		int step=1000;
		int nb_population=5;
		int nb_clones=5;
		PriorityQueue<Simulation> population= new PriorityQueue<Simulation>(100,new Simulation.SimuComparator());
		PriorityQueue<Simulation> population2= new PriorityQueue<Simulation>(100,new Simulation.SimuComparator());
		for (int i=0; i<nb_population; i++){
			population.add(new Simulation());
		}
		
		for (int t=0; t<54000; t+=step){
			population2= new PriorityQueue<Simulation>(100,new Simulation.SimuComparator());
			System.out.println(population.size());
			
			for (int j=0; j<nb_population; j++){
				Simulation s= population.poll();
				System.out.println(s.score);
				for (Simulation s2 : s.clone(nb_clones)){
					s2.launcheSimulation(t,step);
					population2.add(s2);
				}
			}
			population=population2;
			System.out.println(t);
		}
		
		Simulation s=population.poll();
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
