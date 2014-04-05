package com.google;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Simulation {
	int[] visitedEdges;
	PriorityQueue<Car> events;
	double score=0.0;
	
	
	public Simulation(){
		visitedEdges=new int[Edge.edges.length];
		events= new PriorityQueue<Car>(8, new Car.CarComparator());
		for (int i=0; i<Car.cars.length; i++)
			events.add(Car.cars[i].clone());
		
	}
	public void launcheSimulation(double start){
		score=Car.moveCars(start + 1000, visitedEdges, events, score);
		
	}
	
	public List<Simulation> clone(int number_of_clone){
		LinkedList<Simulation> simus= new LinkedList<Simulation>();
		for (int i=0; i<number_of_clone; i++){
			Simulation s=new Simulation();
			s.visitedEdges=visitedEdges.clone();			
			s.events= new PriorityQueue<Car>(8, new Car.CarComparator());
			for (Car c : events){
				s.events.add(c.clone());
			}
			s.score=score;
			simus.add(s);
		
		}
		return simus;
	}
	
	
	
}
