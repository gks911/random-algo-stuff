/**
 * 
 */
package edu.gks.random;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gaurav
 *
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
	
	private class Node{
		private Node left,right;
		private Key key;
		private Value value;
		int weight;
		
		public Node(Key key, Value value, int n){
			this.key=key;
			this.value=value;
			this.weight=n;
		}
	}
	
	Node root;

	/**
	 * Weight of the tree rooted at this node
	 * @param node
	 * @return
	 */
	public int getWeight(Node node){
		if (node==null)
				return 0;
		else return node.weight;
	}
	
	/**
	 * Insert a value
	 * @param key
	 * @param val
	 * @return the root of the tree
	 */
	public void insert(Key key, Value val){
		if (root==null)
			root=new Node(key,val,1);
		else root=_insert(root,key,val);
	}
	
	/**
	 * Helper method for inserting a value
	 * @param node
	 * @param key
	 * @param val
	 * @return
	 */
	private BinarySearchTree<Key, Value>.Node _insert(
			BinarySearchTree<Key, Value>.Node node, Key key, Value val) {
		if(node==null)
			return new Node(key,val,1);
		if (key.compareTo(node.key) == -1)
			node.left=_insert(node.left, key, val);
		else if (key.compareTo(node.key) == 1)
			node.right=_insert(node.right, key, val);
		else
			node.value=val;
		node.weight=getWeight(node.left)+getWeight(node.right)+1;
		return node;
	}

	/**
	 * Search for a given key
	 * @param key
	 * @return
	 */
	public Value search(Key key){
		if(root==null) 
			return null;
		else
			return _search(root, key);
	}
	
	/**
	 * Helper method for doing a search
	 * @param node
	 * @param key
	 * @return
	 */
	private Value _search(BinarySearchTree<Key, Value>.Node node, Key key) {
		if (node==null) return null;
		if (key.compareTo(node.key) == 0)
			return node.value;
		else if (key.compareTo(node.key) == -1)
			return _search(node.left, key);
		else if (key.compareTo(node.key) == 1)
			return _search(node.right, key);
		else
			return null;
	}

	/**
	 * Computes the floor of the given key
	 * @param key
	 * @return
	 */
	public Key floor(Key key){
		if (root==null)
			return null;
		else
			return _floor(root,key).key;
	}
	
	/**
	 * Helper method to find the floor of the given key
	 * @param node
	 * @param key
	 * @return Node having the floor value.
	 */
	private Node _floor(BinarySearchTree<Key, Value>.Node node, Key key) {
		if (node==null) return null;
		if(key.compareTo(node.key)==0)
			return node;
		else if(key.compareTo(node.key)==-1)
			return _floor(node.left,key);
		Node parent=_floor(node.right, key);
		if (parent!=null)
			return parent;
		else return node;
	}
	
	/**
	 * Computes the ceiling of the given key
	 * @param key
	 * @return
	 */
	public Key ceil(Key key){
		if (root==null)
			return null;
		else
			return _ceil(root,key).key;
	}
	
	/**
	 * Helper method to compute the ceiling of the given key
	 * @param node
	 * @param key
	 * @return
	 */
	private Node _ceil(BinarySearchTree<Key, Value>.Node node, Key key) {
		if (node==null) return null;
		if(key.compareTo(node.key)==0)
			return node;
		else if(key.compareTo(node.key)==1)
			return _ceil(node.right,key);
		Node parent=_ceil(node.left, key);
		if (parent!=null)
			return parent;
		else return node;
	}

	public int rank(Key key){
		if (root==null) return -1;
		else
			return _rank(root,key);
	}
	
	
	private int _rank(BinarySearchTree<Key, Value>.Node node, Key key) {
		if(node==null) return 0;
		if(key.compareTo(node.key)==0)
			return getWeight(node.left);
		else if(key.compareTo(node.key)==-1)
			return _rank(node.left, key);
		else
			return getWeight(node.left)+1+_rank(node.right,key);
	}

	/**
	 * Calculates the height of the tree
	 * @return
	 */
	public int height(){
		if(root==null) return 0;
		else
			return _height(root);
	}
	
	private int _height(BinarySearchTree<Key, Value>.Node node) {
		if(node==null) return 0;
		else{
			int left=_height(node.left);
			int right=_height(node.right);
			return Math.max(left, right)+1;
		}
	}

	/**
	 * Calculates the diameter of the tree
	 * @return
	 */
	public int diameter(){
		if (root==null) return 0;
		else
			return _diameter(root);
	}
	
	private int _diameter(BinarySearchTree<Key, Value>.Node node) {
		if (node==null) return 0;
		else 
			// O(n^2). Simply. Too. Bad.
			return Math.max(Math.max(_height(node.left), _height(node.right)), _height(node)+1);
	}

	/**
	 * Calculates the lowest common ancestor of the two given Keys.
	 * NOTE: Assumes both the keys are already present in the BST. If not, returns root.
	 * @param key1
	 * @param key2
	 * @return
	 */
	public Key lowestCommonAncestor(Key key1, Key key2){
		if (root==null) return null;
		else
			return _lca(root, key1, key2).key;
	}	
	
	private Node _lca(BinarySearchTree<Key, Value>.Node node,
			Key key1, Key key2) {
		if(key1.compareTo(node.key)==-1 && key2.compareTo(node.key)==-1)
			return _lca(node.left,key1,key2);
		else if(key1.compareTo(node.key)==1 && key2.compareTo(node.key)==1)
			return _lca(node.right,key1,key2);
		else
			return node;
	}

	/**
	 * Prints the tree in a level ordered way
	 * Does a BFS
	 */
	public void printLevelOrder(){
		if(root==null) return;
		Queue<BinarySearchTree<Key,Value>.Node> queue = new LinkedList<BinarySearchTree<Key,Value>.Node>();
		queue.add(root);
		int levelOne=1,levelTwo=0;
		while(!queue.isEmpty()){
			BinarySearchTree<Key,Value>.Node _tmp=queue.poll();
			levelOne--;
			System.out.print(_tmp.key+" ");
			if(_tmp.left!=null){
				queue.add(_tmp.left);
				levelTwo++;
			}
			if(_tmp.right!=null){
				queue.add(_tmp.right);
				levelTwo++;
			}
			if(levelOne==0){
				System.out.println();
				levelOne=levelTwo;
				levelTwo=0;
			}
		}
	}
	
	public void printTree(){
		if(root==null) return;
		int spaces= 1<<height();
		Queue<BinarySearchTree<Key,Value>.Node> queue = new LinkedList<BinarySearchTree<Key,Value>.Node>();
		queue.add(root);
		int levelOne=1,levelTwo=0, curLevel=1,forTime=1;
		while(!queue.isEmpty()){
			BinarySearchTree<Key,Value>.Node _tmp=queue.poll();
			levelOne--;
			int limit = forTime>1?2:1;
			for(int i=0;i<limit*(spaces/(1<<curLevel));i++)
				System.out.print(" ");
			System.out.print("["+_tmp.key+"]");
			if(_tmp.left!=null){
				queue.add(_tmp.left);
				levelTwo++;
			}
			if(_tmp.right!=null){
				queue.add(_tmp.right);
				levelTwo++;
			}
			if(levelOne==0){
				System.out.println();
				levelOne=levelTwo;
				levelTwo=0;
				curLevel++;
				forTime=1;
			}else
				forTime++;
		}
	}
	
	/**
	 * Will only work for integer keys
	 * @return
	 */
	public boolean isBST(){
		if(root==null) return true;
		else return _isBST(root);
	}
	
	private boolean _isBST(BinarySearchTree<Key, Value>.Node node) {
		//May give wrong results. Why/When?

//		boolean _left=_isBST(node.left, min, node.key);
//		if(!_left) return false;
//		boolean _right=_isBST(node.right, node.key, max);
//		return _right;
		
		if(node==null || node.left==null || node.right==null) return true;
		if ((node.key.compareTo(node.left.key) == 1 || node.key
				.compareTo(node.left.key) == 0)
				&& (node.key.compareTo(node.right.key) == -1 || node.key
						.compareTo(node.right.key) == 0))
			return _isBST(node.left) & _isBST(node.right);
		else return false;
	}

	/**
	 * Delete a given key from the tree
	 * @param key
	 * @return
	 */
	public Node delete(Key key){
		if(root==null) return null;
		else return _delete(root,key);
	}
	
	/**
	 * Helper method to delete a key from the node
	 * @param node
	 * @param key
	 * @return
	 */
	private BinarySearchTree<Key, Value>.Node _delete(
			BinarySearchTree<Key, Value>.Node node, Key key) {
		if(node==null) return null;
		if(key.compareTo(node.key)==-1)
			node.left=_delete(node.left, key);
		else if(key.compareTo(node.key)==1)
			node.right=_delete(node.right, key);
		else{
			//key=node.key
			if(node.left==null) return node.right;
			if(node.right==null) return node.left;
			Node _tmp=node;
			node=_getMin(node.right);
			node.right=_deleteMin(_tmp.right);
			node.left=_tmp.left;
		}
		node.weight=getWeight(node.left)+getWeight(node.right)+1;
		return node;
	}

	private BinarySearchTree<Key, Value>.Node _getMin(Node node) {
		if(node==null) return null; 
		if(node.left==null) return node;
		return _getMin(node.left);
	}
	
	private BinarySearchTree<Key, Value>.Node _deleteMin(Node node) {
		if(node.left==null) return node.right;
		node.left=_deleteMin(node.left);
		node.weight=getWeight(node.left)+getWeight(node.right)+1;
		return node;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
		
		bst.insert(27, 27);
		bst.insert(8, 8);
		bst.insert(23, 23);
		bst.insert(25, 25);
		bst.insert(6, 6);
		bst.insert(20, 20);
		bst.insert(2, 2);
		bst.insert(31, 31);
		bst.insert(7, 7);
		bst.insert(11, 11);
		
		System.out.println(bst.search(311));
		System.out.println(bst.floor(322));
		System.out.println(bst.ceil(29));
		System.out.println("Rank = "+bst.rank(6));
		System.out.println("Height = "+bst.height());
		System.out.println("Diameter = "+bst.diameter());
		System.out.println("LCA = "+bst.lowestCommonAncestor(1, 111));
		System.out.println("Level Order  Traversal: ");
		bst.printLevelOrder();
		bst.delete(23);
		System.out.println("Level Order  Traversal: ");
		bst.printLevelOrder();
		System.out.println("IsBST? : "+bst.isBST());
		bst.printTree();
		
	}
}