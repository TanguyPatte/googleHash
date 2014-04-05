package com.google;

import java.util.LinkedList;
import java.util.List;

public class Node {
	public static class ConnectedEdge {
		public int edge;
		public int to;
	}

	public static Node[] nodes;
	public static double sigma = 2.0;

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

	public double evaluate(int from) {
		if (!canGoTo(from, index))
			return 0.0;

		double score = 0.0;
		for (ConnectedEdge c_edge : edges) {
			if (!canGoTo(index, c_edge.to))
				continue;

			Edge edge = Edge.edges[c_edge.edge];
			score += edge.distance / edge.cost * Math.exp(-edge.visited/(2.0*sigma*sigma));
		}

		return score;
	}

	public int pickNext() {
		double[] scores = new double[edges.size()];
		double tot = 0.0;

		for (int i = 0 ; i < edges.size() ; ++i) {
			
			scores[i] = Node.nodes[edges.get(i).to].evaluate(index);
			tot += scores[i];
			System.out.println("index "+String.valueOf(edges.get(i).to)+" score " + String.valueOf(scores[i]));
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

	public static boolean canGoTo(int from, int to) {
		Edge edge = Edge.edges[edgeBetween(from, to)];
		if (!edge.oneWay)
			return true;
		return edge.from == from;
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


