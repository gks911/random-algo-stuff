/**
 * 
 */
package edu.gks.random;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaurav
 *
 */
public class Graph<T extends Node> {

	int vertices;
	int edges;
	List<T>[] adjList;
	
	@SuppressWarnings("unchecked")
	public Graph(int vertices){
		this.vertices=vertices;
		adjList=(ArrayList<T>[]) new ArrayList[vertices];
		for(int i=0;i<vertices;i++){
			adjList[i]=new ArrayList<T>();
		}
	}
	
	public void addEdge(T u,T v){
		adjList[u.n].add(v);
		adjList[v.n].add(u);
		edges+=1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Node{
	int n;
	char c;
	int row,col;
	
	public Node(int n,char c,int row,int col){
		this.n=n;
		this.c=c;
		this.row=row;
		this.col=col;
	}
}