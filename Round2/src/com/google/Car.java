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
 	public static PriorityQueue<Car> events = new PriorityQueue<Car>(50000, new CarComparator());
	
	public List<Integer> history = new LinkedList<Integer>();
	public int currentNode;
	public int nextNode;
	public double nextNodeArrivalTime;
	
	public Car() {
		history.add(firstNode);
		currentNode = firstNode;
		nextNodeArrivalTime = 0.0;
		events.add(this);
	}
	
	public static void moveCars() {
		
	}
}
