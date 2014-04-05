package com.google;

import java.util.Arrays;
import java.util.List;

import sun.awt.geom.AreaOp.AddOp;

public class Reader {

	public static double time ;
	
	public static void readFile(){
		List<String> l = IOSimple.readFile("paris_54000.txt");
		String p[] = l.get(0).split(" ");
		
		int initValues[] = new int[p.length];
		int nbNode = Integer.parseInt(p[0]) ;
		int nbEdge = Integer.parseInt(p[1]) ;
		time = Double.parseDouble(p[2]) ;
		Reader.time = time;
		int nbCars = Integer.parseInt(p[3]) ;
		int firstNode = Integer.parseInt(p[4]) ;
		int index = 1;
		Node.nodes = new Node[nbNode];
		System.out.println(time);
		Car.firstNode = firstNode;
		Car.cars = new Car[nbCars];
		for(int i = 0; i< nbCars; i++){
			Car.cars[i] = new Car(i);
		}
		
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
			
			Node.nodes[Integer.parseInt(tmp[1])].addEdge(newEdge);
			
			
			Edge.edges[index - nbNode - 1]=newEdge;
			
			index++;
		}
	}
}
