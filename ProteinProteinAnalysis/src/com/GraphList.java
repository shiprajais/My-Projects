package com;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.graphstream.algorithm.BetweennessCentrality;


	
	public class GraphList{
		
		private int vertices;
		public  Map<Integer, List<Integer>> graph;
		public  Hashtable<Integer, Boolean> marked;
		
		
		private Map<Integer, List<Integer>> buildGraphList() throws IOException {
	        Map<Integer,List<Integer>> nodeList = new HashMap<>();
	        
	        String filename = "yeast.txt";
	        File file = new File(filename);
	        System.out.println(filename);
			FileReader reader = new FileReader(file);
			BufferedReader buf = new BufferedReader(reader);
			marked = new Hashtable<>();
			String line;
			while((line = buf.readLine())!=null){
				String[] reads = line.split(" ");
				String keyStr = reads[0].substring(4,reads[0].length()-1);
				String valueStr = reads[1].substring(4,reads[1].length()-1);
				int key =  Integer.parseInt(keyStr);
				int value = Integer.parseInt(valueStr);
				if(!nodeList.containsKey(key)){
					List<Integer> list = new ArrayList<>();
					list.add(value);
					nodeList.put(key,list);
					marked.put(key, false);					
				}
				else{
					List<Integer> list = nodeList.get(key);
					list.add(value);
				}
				
				if(nodeList.containsKey(value)==false){
					List<Integer> list = new ArrayList<>();					
					marked.put(value, false);
				}
				
				
	        }
			
	        return nodeList;
	    }
		
		
		
		public int getVertices() {
			return vertices;
		}


		public static void main(String[] args) throws IOException {
			GraphList gr = new GraphList();
	      
	        gr.graph = gr.buildGraphList();
	        gr.vertices = gr.graph.size();
	        
	        GraphProperties gp = new GraphProperties();
	        
	        gp.graphDiameterRadius(gr);
	        gp.clusteringCoefficient(gr);
	        gp.degreeCentrality(gr);
	        gp.closeness();
	       // System.out.println("graph size "+gr.graph.size());
	        
	        BCentrality bc = new BCentrality();
	      //  bc.buildGraph(gr);
	    }


	}

