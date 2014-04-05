package com.google;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Car {
	public static class CarComparator implements Comparator<Car> {
		public int compare(Car c1, Car c2) {
			if (c1.nextNodeArrivalTime > c2.nextNodeArrivalTime)
				return -1;
			if (c1.nextNodeArrivalTime == c2.nextNodeArrivalTime)
				return 0;
			return 1;
		}
	}

	public static int firstNode;
	public static double deltaTime = 1000;
	public static Car[] cars;

	public final int index;
	public int currentNode;
	public int nextNode;
	public double nextNodeArrivalTime;
	public List<Integer> history = new LinkedList<Integer>();

	public Car(int index) {
		this.index = index;
		currentNode = nextNode = firstNode;
		nextNodeArrivalTime = 0.0;
	}

	@Override
	public Car clone() {
		Car newCar = new Car(index);
		newCar.currentNode = currentNode;
		newCar.nextNode = newCar.nextNode;
		newCar.nextNodeArrivalTime = nextNodeArrivalTime;
		for (int i : history)
			newCar.history.add(i);
		return newCar;
	}
	
	public static double moveCars(double T, int[] visitedEdges, PriorityQueue<Car> events, double score) {
		double time = 0;
		
		while (time <= T) {
			// Peek the first car to arrive somewhere
			Car car = events.peek();
			
			// Check the time
			time = car.nextNodeArrivalTime;
			if (time > T) 
				return score;
			events.poll();
			
			// Update the history of the car
			car.history.add(car.nextNode);
			car.currentNode = car.nextNode;

			// Peek the next destination
			car.nextNode = Node.nodes[car.currentNode].pickNext(visitedEdges);
			Edge edge = Edge.edges[Node.edgeBetween(car.currentNode, car.nextNode)];
			car.nextNodeArrivalTime += edge.cost;
			if (visitedEdges[edge.index] == 0) 
				score += edge.distance;
			++visitedEdges[edge.index];
			
			//System.out.println("Time: " + time + " -> Car " + car.index + " arrives to " + car.currentNode + " and goes to " + car.nextNode);
			
			// Add the event to the events queue
			events.add(car);
		}
		return score;
	}
}
