package com.google;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Simulation {
	public static class SimuComparator implements Comparator<Simulation> {
		public int compare(Simulation c1, Simulation c2) {
			if (c1.score < c2.score)
				return -1;
			if (c1.score == c2.score)
				return 0;
			return 1;
		}
	}
	int[] visitedEdges;
	PriorityQueue<Car> events;
	double score=0.0;
	
	
	public Simulation(){
		visitedEdges=new int[Edge.edges.length];
		events= new PriorityQueue<Car>(8, new Car.CarComparator());
		for (int i=0; i<Car.cars.length; i++)
			events.add(Car.cars[i].clone());
		
	}
	public void launcheSimulation(double start, double step){
		score = Car.moveCars(start + step, visitedEdges, events, score);
	}
	
	public Simulation clone() {
		Simulation s = new Simulation();
		
		s.visitedEdges = visitedEdges.clone();	
		s.score = score;
		s.events.clear(); 
		for (Car c : events)
			s.events.add(c.clone());
		
		return s;
	}
	
	public List<Simulation> clone(int number_of_clone){
		LinkedList<Simulation> simus = new LinkedList<Simulation>();
		for (int i=0; i<number_of_clone; i++) 
			simus.add(this.clone());
		return simus;
	}
	
	
	
}
