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
	public final double latitude;
	public final double longitude;
	public final int index;

	public Node(int index, double latitude, double longitude) {
		this.index = index;
		this.latitude = latitude;
		this.longitude = longitude;
	}

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

			score += edge.distance / edge.cost * Math.exp(-edge.visited/2.0);
		}

		return score;
	}

	public int pickNext() {
		double[] scores = new double[edges.size()];
		double tot = 0.0;

		for (int i = 0 ; i < edges.size() ; ++i) {
			scores[i] = Node.nodes[edges.get(i).to].evaluate();
			tot += scores[i];
		}

		double rand = Math.random()*tot;
		double acc = 0.0;
		for (int i = 0; i < edges.size(); ++i) {
			if (acc >= rand)
				return edges.get(i).to;
			acc += scores[i];
		}

		return edges.get(edges.size()-1).to; // Never reached
	}

	public static int edgeBetween(int from, int to) {
		Node fromNode = nodes[from];
		for (ConnectedEdge c_edge : fromNode.edges) {
			if (c_edge.to == to)
				return c_edge.edge;
		}
		return -1;
	}
}


