package com.google;

import java.util.LinkedList;
import java.util.List;

public class Node {
	public static class ConnectedEdge {
		public int edge;
		public int to;
	}
	
	public static Node[] nodes;
	
	public List<ConnectedEdge> edges = new LinkedList<ConnectedEdge>();
	public double latitude;
	public double longitude;
	public int index;
	
	public void addEdge(Edge edge) {
		ConnectedEdge c_edge = new ConnectedEdge();
		c_edge.edge = edge.index;
		if (edge.to == index)
			c_edge.to = edge.from;
		else
			c_edge.to = edge.to;
		edges.add(c_edge);
	}
	
	public double evaluate() {
		double score = 0.0;
		
		for (ConnectedEdge c_edge : edges) {
			Edge edge = Edge.edges[c_edge.edge];
			
			if (edge.oneWay && edge.to == index)
				continue;
			
			score += edge.distance / (edge.cost * (edge.visited + 1));
		}
		
		return score;
	}
}


