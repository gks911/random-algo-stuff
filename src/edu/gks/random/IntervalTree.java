/**
 * 
 */
package edu.gks.random;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaurav
 * Only for integers
 */
public class IntervalTree {

	private class Node{
		Node left,right;
		int low,high;
		int max;		
		
		public Node(int low,int high){
			this.low=low;
			this.high=high;
			this.max=high;
		}

		@Override
		public String toString() {
			return "["+low+","+high+"]:"+max;
		}
	}
	
	Node root_;
	
	public void insert(int low, int high){
		if (root_==null) 
			root_=new Node(low,high);
		else
			root_=_insert(root_,low,high);
	}
	
	private Node _insert(Node node, int low, int high) {
		if(node==null)
			return new Node(low,high);
		if(low<node.low)
			node.left=_insert(node.left, low, high);
		else if (low>node.low)
			node.right=_insert(node.right, low, high);
		else{
			//overwrite older values
			node.low=low;
			node.high=high;
		}
		node.max=Math.max(Math.max((node.left==null)?0:node.left.max, (node.right==null)?0:node.right.max),node.max);
		return node;
	}

	public Node search(int low,int high){
		if(root_==null) return null;
		else return _search(root_,low,high);
	}
	
	private Node _search(Node node, int low, int high) {
		if(node==null) return null;
		else if(_intersects(node,low,high)) return node;
		else if(node.left!=null && low<node.left.max) return _search(node.left,low,high);
		else return _search(node.right,low,high);
	}

	public void searchAllIntervals(int low,int high,List<Node> list){
		if(root_==null) return;
		else _searchAllIntervals(root_,low,high,list);
	}
	
	/**
	 * Does an in-order traversal
	 * O(n) time
	 * @param node
	 * @param low
	 * @param high
	 * @param list
	 */
	private void _searchAllIntervals(Node node, int low, int high, List<Node> list) {
		if(node==null) return;
		_searchAllIntervals(node.left,low,high,list);
		if(_intersects(node,low,high)) list.add(node);
		_searchAllIntervals(node.right,low,high,list);
	}
	
	private boolean _intersects(Node node, int low, int high) {
		if(node.low<=high&& node.high>=low)
			return true;
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntervalTree it = new IntervalTree();
		it.insert(16, 21);
		it.insert(8, 9);
		it.insert(25, 30);
		it.insert(5, 8);
		it.insert(15, 23);
		it.insert(17, 19);
		it.insert(26, 26);
		it.insert(0, 3);
		it.insert(6, 10);
		it.insert(19, 20);
//		System.out.println(it.search(2, 18));
		List<Node> list = new ArrayList<IntervalTree.Node>();
		it.searchAllIntervals(0, 30, list);
		for(Node n:list)
			System.out.println(n);
	}

}
