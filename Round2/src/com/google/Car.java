package com.google;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
	public static double deltaTime = 1000;
	public static Car[] cars;

	public final int index;
	public int currentNode;
	public int nextNode;
	public double nextNodeArrivalTime;

	public Car(int index) {
		this.index = index;
		currentNode = nextNode = firstNode;
		nextNodeArrivalTime = 0.0;
	}

	public static double moveCars(double T, int[] visitedEdges, Map<Car, LinkedList<Integer>> histories, PriorityQueue<Car> events) {
		double time = 0;
		
		while (time <= T) {
			// Peek the first car to arrive somewhere
			Car car = events.poll();
			
			// Check the time
			time = car.nextNodeArrivalTime;
			if (time > T)
				return time;
			
			// Update the history of the car
			histories.get(car).add(car.nextNode);
			car.currentNode = car.nextNode;

			// Peek the next destination
			car.nextNode = Node.nodes[car.currentNode].pickNext(visitedEdges);
			Edge edge = Edge.edges[Node.edgeBetween(car.currentNode, car.nextNode)];
			car.nextNodeArrivalTime += edge.cost;
			++visitedEdges[edge.index];
			
			//	System.out.println("Time: " + time + " -> Car " + car.index + " arrives to " + car.currentNode + " and goes to " + car.nextNode);
			
			// Add the event to the events queue
			events.add(car);
		}
		return time;
	}
	
	public static void moveCars(double T) {
		double time = 0;
		double bestTime = 0;
		double score, maxScore = 0;
		int[] lastVisitedEdges = new int[Edge.edges.length];
		int[] bestVisitedEdges, visitedEdges;
		Map<Car, LinkedList<Integer>> lastHistories = new HashMap<Car, LinkedList<Integer>>();
		Map<Car, LinkedList<Integer>> bestHistories, histories;
		PriorityQueue<Car> lastEvents = new PriorityQueue<Car>();
		PriorityQueue<Car> bestEvents, events;
		
		while (bestTime <= T) {
			
			// Restore the saved state
			visitedEdges = lastVisitedEdges.clone();
			histories = lastHistories
			
			time = moveCars(deltaTime, visitedEdges, histories);
			score = computeScore();
			if (score > maxScore) {
				maxScore = score;
				bestVisitedEdges = visitedEdges.clone();
			}
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
