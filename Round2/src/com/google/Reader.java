package com.google;

import java.util.List;

public class Reader {

	
	public static void readFile(){
		List<String> l = IOSimple.readFile("googleTest.txt");
		System.out.println(l.get(0));
		String p[] = l.get(0).split(" ");
		int initValues[] = new int[p.length];
		int nbNode = Integer.parseInt(p[0]) ;
		int nbEdge = Integer.parseInt(p[1]) ;
		int time = Integer.parseInt(p[2]) ;
		int nbCars = Integer.parseInt(p[3]) ;
		int firstNode = Integer.parseInt(p[4]) ;
		int index = 1;
		Node.nodes = new Node[nbNode];
		
		while(index <= nbNode ){
			String tmp[] = l.get(index).split(" ");
			Node.nodes[index -1] = new Node(index -1, Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1]));
			index ++;
		}
		System.out.println(l.get(index));
		System.out.println("nb edge : " + nbEdge);
		Edge.edges=new Edge[nbEdge];
		
		while(index <= nbNode + nbEdge){
			String tmp[] = l.get(index).split(" ");
	//		System.out.println(index - nbNode - 1);
			boolean sensunique=Integer.parseInt(tmp[2]) == 1 ? true :false;
			Edge newEdge=new Edge(index - nbNode - 1,Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]), Double.parseDouble(tmp[4]), Double.parseDouble(tmp[3]), sensunique    );
			Node.nodes[Integer.parseInt(tmp[0])].addEdge(newEdge);
			Edge.edges[index - nbNode - 1]=newEdge;
			
			index++;
		}
		
	}
}
