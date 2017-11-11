package com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.graphstream.algorithm.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.Viewer;

public class BCentrality {

	public void buildGraph(GraphList G) throws IOException{
		Graph graph = new MultiGraph("yeast");
		
		Set<Integer> keys = G.graph.keySet();
		ArrayList<Node>  nodes = new ArrayList<>();
		
		for(Integer key : keys){
			Node node = graph.addNode(""+key);
			nodes.add(node);
		}
		for(Integer key : keys){
			for(Integer edges: G.graph.get(key)){
				
				graph.addEdge(key+""+edges, ""+key, ""+edges);
			}
		}
		BetweennessCentrality bcb = new BetweennessCentrality();
        bcb.init(graph);
        bcb.compute();
        //FileWriter fw = new FileWriter(new File("centrality_yeast.txt"));
        //BufferedWriter bw = new BufferedWriter(fw);
        for(Node node: nodes){
        	System.out.println(node+"betweeness centrality "+node.getAttribute("Cb")+"\n");
        }
        
        
        ArrayList<Double>  bCentrality =  new ArrayList<>();
       
		
		
	   // Viewer viewer = graph.display();
	}

}
