package com.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Node {
	public static class ConnectedEdge {
		public int edge;
		public int to;
	}

	public static class EdgeScore {
		public int edge;
		public double score;
	}

	public static class EdgeScoreComparator implements Comparator<EdgeScore> {
		public int compare(EdgeScore s1, EdgeScore s2) {
			if (s1.score > s2.score)
				return -1;
			if (s1.score == s2.score)
				return 0;
			return 1;
		}
	}

	public static Node[] nodes;
	public static double sigma = 0.9;

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

	public double evaluate(int from, int[] visitedEdges) {
		if (!canGoTo(from, index))
			return 0.0;

		double score = 0.0001;
		for (ConnectedEdge c_edge : edges) {
			if (!canGoTo(index, c_edge.to))
				continue;

			Edge edge = Edge.edges[c_edge.edge];
			//*
			score += edge.distance / edge.cost * Math.exp(-visitedEdges[c_edge.edge]/(2*sigma*sigma));
			/*/
			if (visitedEdges[c_edge.edge] == 0) 
				score = Math.max(score, edge.distance / edge.cost);
			//*/
		}

		return score;
	}

	public int pickNext(int[] visitedEdges) {
		List<EdgeScore> scores = new ArrayList<EdgeScore>();
		double tot = 0.0;

		for (int i = 0 ; i < edges.size() ; ++i) {
			EdgeScore score = new EdgeScore();
			score.edge = edges.get(i).to;
			score.score = Node.nodes[score.edge].evaluate(index, visitedEdges);
			tot += score.score;
			scores.add(score);
		}

		Collections.sort(scores, new EdgeScoreComparator());

		double rand = Math.random()*tot;
		double acc = 0.0;
		for (int i = 0; i < edges.size(); ++i) {
			acc += scores.get(i).score;
			if (acc >= rand) {
				return scores.get(i).edge;
			}
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

	public static double distanceBetween(int from, int to) {
		Node fromNode = nodes[from];
		Node toNode = nodes[to];

		double dx = fromNode.latitude - toNode.latitude;
		double dy = fromNode.longitude - toNode.longitude;

		return dx*dx+dy+dy;
	}
}


