package com;


import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFSTraverse
{
	
	Hashtable<Integer, Boolean> marked;
	Hashtable<Integer, Boolean> marked1;
	
	protected Hashtable<Integer,Integer> dis;
	
	protected int e;
	
	protected int distance;
	
	int vertex;

	
	public BFSTraverse(GraphList G, Integer s)
	{
		vertex = s;
		marked = G.marked;		
		dis = new Hashtable(G.getVertices());
		bfs(G, s);
	}

	
	private void bfs(GraphList G, int s)
	{
		Queue<Integer> q = new LinkedList<Integer>();
		
		dis.put(s, 0);
		q.add(s);
		
		marked.put(s, true);
		while (!q.isEmpty())
		{
			Integer v = q.remove();
			//System.out.println("v is: "+v+" graph "+G.graph.get(v));
			List<Integer> list = G.graph.get(v);
			if(list!=null){
				for (Integer w : list)
					
					if (marked.get(w)==false)
					{
						marked.put(w, true);
						if(dis.get(v) == null){
							dis.put(w, 1);
						}
						else{
							dis.put(w, dis.get(v)+1);
						}
						q.add(w);
					}
			}
		}

		// calculate eccentricity of s
		Set<Integer> keys = dis.keySet();
		for (Integer key:keys)
			if (dis.get(key) > e)
				e = dis.get(key);

	}

	

	
}
