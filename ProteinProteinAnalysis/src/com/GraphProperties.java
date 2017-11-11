package com;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class GraphProperties {
	
	private ArrayList<BFSTraverse> bfs = null;
	
	
	private int diameter;
	
	private int radius;
	
	private HashMap<Integer, Double> closeness;
	
	
	public void graphDiameterRadius(GraphList G)
	{
		bfs = new ArrayList<BFSTraverse>(G.getVertices());
		
		Set<Integer> keys = G.graph.keySet();
		for(int key : keys){
			bfs.add(new BFSTraverse(G, key));
		}
		
		diameter = 0;
		radius = Integer.MAX_VALUE;
		for (int v = 0; v < bfs.size(); v++)
		{
			if (diameter < bfs.get(v).e)
				diameter = bfs.get(v).e;
			
			if (radius > bfs.get(v).e)
				radius = bfs.get(v).e;
			
		}
		System.out.println("diameter is: "+diameter);
		System.out.println("radius is: "+radius);
	}
	

	public void clusteringCoefficient(GraphList G){
		Set<Integer> keys = G.graph.keySet();
		double partialSum=0;
		for(int key : keys){
			double actual=0;
			double degree = G.graph.get(key).size();
			double possible = degree*(degree-1)/2;
			List<Integer> list = G.graph.get(key);
			if(possible>0){
				for(int node1 : list){
					for(int node2 : list){
						if( (G.graph.get(node1)!=null) && (G.graph.get(node1).contains(node2)) ){
							actual++;
						}					
					}
				}
				double localCoeff = actual/possible;
				partialSum += localCoeff;
			}
		}
		double clusteringCoeff = partialSum/G.graph.size();
		System.out.println("clustering coefficient "+clusteringCoeff);
	}

	public void degreeCentrality(GraphList G){
		
		Set<Integer> keys = G.graph.keySet();
		double degreeCentrality;
		double max =0;
		for(int key : keys){
			double degree = G.graph.get(key).size();
			if(degree>max){
				max = degree;
			}
		}
		//System.out.println("max is: "+max);
		double sum =0;
		for(int key : keys){
			double degree = G.graph.get(key).size();
			sum = sum + (max-degree); 
		}
		
		int v = G.getVertices();
		//System.out.println("v is "+v);
		double denom = (v-1)*(v-2);
		//System.out.println("sum is "+sum+" denom is "+denom);
		degreeCentrality = sum/denom;
		
		System.out.println("degree centrality: "+degreeCentrality);
	}
	
	public void closeness(){
		closeness = new HashMap<>();
		ArrayList<Double> maxClose  = new ArrayList<>();
		for (int v = 0; v < bfs.size(); v++)
		{
			Hashtable<Integer, Integer> dis = bfs.get(v).dis;
			double close = 0;
			for (int i = 0; i < dis.size(); i++) {
				if(dis.get(i)!=null){
					close += Math.pow(2, -dis.get(i));
				}
			}
			closeness.put(bfs.get(v).vertex, close);
			maxClose.add(close);
		}
		Collections.sort(maxClose);
		System.out.println("closeness maximum: "+maxClose.get(maxClose.size()-1));
		System.out.println("closeness is: "+closeness);
		
		
	}
}
