package com.google;

import java.util.LinkedList;
import java.util.List;

public class Node {
	public static class ConnectedEdge {
		public int edge;
		public int to;
	}
	
	public List<ConnectedEdge> edges = new LinkedList<ConnectedEdge>();
}


