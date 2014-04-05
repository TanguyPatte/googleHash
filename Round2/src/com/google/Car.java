package com.google;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Car {
	public static class CarComparator implements Comparator<Car> {
		public int compare(Car c1, Car c2) {
			if (c1.nextNodeArrivalTime < c2.nextNodeArrivalTime)
				return -1;
			if (c1.nextNodeArrivalTime == c2.nextNodeArrivalTime)
				return 0;
			return 1;
		}
	}

	public static int firstNode;
	public static Car[] cars;
	public static PriorityQueue<Car> events = new PriorityQueue<Car>(8, new CarComparator());

	public final int index;
	public List<Integer> history = new LinkedList<Integer>();
	public int currentNode;
	public int nextNode;
	public double nextNodeArrivalTime;

	public Car(int index) {
		this.index = index;
		currentNode = nextNode = firstNode;
		nextNodeArrivalTime = 0.0;
		events.add(this);
	}

	public static void moveCars(double T) {
		double time = 0.0;

		while (time <= T) {
			// Peek the first car to arrive somewhere
			Car car = events.poll();
			
			// Check the time
			time = car.nextNodeArrivalTime;
			if (time > T)
				return;
			
			// Update the history of the car
			car.history.add(car.nextNode);
			car.currentNode = car.nextNode;

			// Peek the next destination
			car.nextNode = Node.nodes[car.currentNode].pickNext();
			Edge edge = Edge.edges[Node.edgeBetween(car.currentNode, car.nextNode)];
			car.nextNodeArrivalTime += edge.cost;
			++edge.visited;
			
			System.out.println("Time: " + time + " -> Car " + car.index + " arrives to " + car.currentNode + " and goes to " + car.nextNode);
			
			// Add the event to the events queue
			events.add(car);
		}
	}
	
	public static double computeScore() {
		double score = 0.0;
		boolean[] visitedEdges = new boolean[Edge.edges.length];
		for (int i = 0 ; i < Edge.edges.length; ++i) 
			visitedEdges[i] = false;
		
		for (Car car : cars) {
			int currentNode = car.history.get(0);
			for (int i = 1; i < car.history.size() ; ++i) {
				int nextNode = car.history.get(i);
				Edge edge = Edge.edges[Node.edgeBetween(currentNode, nextNode)];
				if (!visitedEdges[edge.index]) {
					score += edge.distance;
					visitedEdges[edge.index] = true;
				}
				currentNode = nextNode;
			}
		}
		
		return score;
	}
}
