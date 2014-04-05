package com.google;

public class Edge {
	public static Edge[] edges;
	
	public final int from;
	public final int to;
	public final double distance;
	public final double cost;
	public final boolean oneWay;
	public final int index;

	public int visited;
	
	public Edge(int index, int from, int to, double distance, double cost, boolean oneWay) {
		this.index = index;
		this.from = from;
		this.to = to;
		this.distance = distance;
		this.cost = cost;
		this.visited = 0;
		this.oneWay = oneWay;
	}
}
