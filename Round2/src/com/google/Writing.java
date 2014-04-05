package com.google;

import java.util.LinkedList;
import java.util.List;


public class Writing {
	public static void writeSolution(List<Node>[] result){
		LinkedList<String> outputString=new LinkedList<String>();
		outputString.add(String.valueOf(result.length));
		for (int i=0; i<result.length; i++){
			outputString.add(String.valueOf(result[i].size()));
			for (Node n : result[i]){
				outputString.add(String.valueOf(n.index));
			}
		}
		
		IOSimple.writeFile("result_"+String.valueOf(System.currentTimeMillis()), outputString);
	}
}
