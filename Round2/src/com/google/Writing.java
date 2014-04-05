package com.google;

import java.util.LinkedList;
import java.util.List;


public class Writing {
	public static void writeSolution(List<LinkedList<Node>> result){
		LinkedList<String> outputString=new LinkedList<String>();
		outputString.add(String.valueOf(result.size()));
		for (List<Node> list_node : result){
			outputString.add(String.valueOf(list_node.size()));
			for (Node n : list_node){
				outputString.add(String.valueOf(n.index));
			}
		}
		
		IOSimple.writeFile("result_"+String.valueOf(System.currentTimeMillis())+".txt", outputString);
	}
}
