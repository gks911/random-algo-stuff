/**
 * 
 */
package edu.gks.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
		
		@SuppressWarnings("unchecked")
		public Node(Integer obj) {
			resetValue(obj, (Class<Value>) Integer.class);
			resetKey(obj, (Class<Key>) Integer.class);
		}

		public void resetValue(Integer obj, Class<Value> cls){
			this.value=cls.cast(obj);
		}
		
		public void resetKey(Integer obj, Class<Key> cls){
			this.key=cls.cast(obj);
		}

		@Override
		public String toString(){
			return this.value.toString();
		}
	}
	
	Node root;
	Node first=null,prev=null;

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
	public Key lca(Key key1, Key key2){
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
	
	public void inOrder(Node node){
		if(node!=null){
			inOrder(node.left);
			System.out.print(node.value+" ");
			inOrder(node.right);
		}
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
	
	/**
	 * Prints a weirdly looking tree. 
	 * TODO: Improve representation.
	 */
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
	 * 1.
	 * @param node
	 * @return
	 */
	public int sizeOfTree(Node node){
		if (node==null) return 0;
		else return sizeOfTree(node.left)+1+sizeOfTree(node.right);
	}
	
	/**
	 * 2.
	 * @param root1
	 * @param root2
	 * @return
	 */
	public boolean isIdentical(Node root1, Node root2){
		if(root1==null && root2==null) return true;
		else if(root1!=null && root2!=null && root1.value==root2.value) return (isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right));
		else return false;
	}
	
	/**
	 * 3.
	 * @param root
	 * @return
	 */
	public Node mirrorTree(Node root){
		if(root!=null){
			mirrorTree(root.left);
			mirrorTree(root.right);
			Node _tmp = root.left;
			root.left=root.right;
			root.right=_tmp;
			return root;
		}
		return null;
	}
	
	/**
	 * 4.
	 * @param root
	 */
	public void printRootToLeafPaths(Node root){
		_printRootToLeafPaths(root,new ArrayList<Node>());
	}
	
	private void _printRootToLeafPaths(Node node,ArrayList<Node> list) {
		if(node==null) return;
		if(node.left==null && node.right==null){
			//we got a leaf node, print the contents of list
			list.add(node);
			System.out.println(list);
			return;
		}else{
			list.add(node);
			ArrayList<Node> _tmpListLeft = new ArrayList<Node>(list);
			ArrayList<Node> _tmpListRight = new ArrayList<Node>(list);
			_printRootToLeafPaths(node.left, _tmpListLeft);
			_printRootToLeafPaths(node.right, _tmpListRight);
		}
	}

	/**
	 * 5.
	 * Print the lowest common ancestor of two nodes in a Binary Tree
	 * TODO: Doesn't handle when one key is absent from the tree
	 * @param node1
	 * @param node2
	 */
	public Node lowestCommonAncestor(Key node1, Key node2){
		Node lca = _lowestCommonAncestor(root, node1, node2);
		if(lca!=null)
			System.out.printf("LCA of %d, %d is : %d\n",node1,node2,lca.key);
		return lca;	
	}
	
	private Node _lowestCommonAncestor(Node root,Key node1,Key node2) {
		if(root==null) return null;
		else if(root.key.compareTo(node1)==0 || root.key.compareTo(node2)==0)
			return root;
		else{
			Node _l = _lowestCommonAncestor(root.left, node1, node2);
			Node _r = _lowestCommonAncestor(root.right, node1, node2);
			if (_l!=null && _r!=null) return root;
			return (_l==null)?_r:_l;
		}
	}

	/**
	 * 6.
	 * @param root
	 */
	public void convertTreeToDLL(Node root){
		if(root!=null){
			_convertTreeToDLL(root);
			Node cur = first;
			while(cur.right!=first){
				System.out.print(cur.value+",");
				cur=cur.right;
			}
			System.out.println(cur);
		}
	}

	private void _convertTreeToDLL(Node cur) {
		// base case
		if (cur == null)
			return;
		// traverse in-order
		_convertTreeToDLL(cur.left);
		// change pointers here
		if (prev != null)
			prev.right = cur;
		else
			first=cur;

		Node right = cur.right;
		cur.right = first;
		cur.left = prev;
		first.left = cur;
		prev = cur;
		_convertTreeToDLL(right);
	}
	
	/**
	 * 7.
	 * @return
	 */
	public boolean isBst(){
		return _isBst(this.root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	
	private boolean _isBst(Node node, int min, int max){
		if(node==null) return true;
		if((Integer)node.value < min || (Integer)node.value > max)
			return false;
		else
			return (_isBst(node.left,min,(Integer)node.value)&&(_isBst(node.right,(Integer)node.value,max)));
	}
	
	/**
	 * 8.
	 */
	public void levelOrderSpiral(){
		Queue<Node> queue = new LinkedList<Node>();
		List<Node> list = new ArrayList<Node>();
		int levelOne=0,levelTwo=0;
		boolean order=false; //true = right to left
		queue.add(this.root);
		levelOne++;
		while(!queue.isEmpty()){
			Node n = queue.poll();
			list.add(n);
			levelOne--;
			if(n.left!=null){
				queue.add(n.left);
				levelTwo++;
			}
			if(n.right!=null){
				queue.add(n.right);
				levelTwo++;
			}
			if(levelOne==0){
				//time to print
				if(order){
					for(int i=list.size()-1;i>=0;i--)
						System.out.print(list.get(i).value+" ");
				}else{
					for(int i=0;i<=list.size()-1;i++)
						System.out.print(list.get(i).value+" ");
				}
				System.out.println();
				list.clear();
				levelOne=levelTwo;
				levelTwo=0;
				order=(order)?false:true;
			}
		}
	}
	
	/**
	 * 9.
	 * @param node
	 * @return
	 */
	public boolean childrenSumProperty(Node node){
		if(node==null || (node.left==null && node.right==null)) return true;
		else{
			int left=0, right=0;
			if(node.left!=null)
				left=(Integer)node.left.value;
			if(node.right!=null)
				right=(Integer)node.right.value;
			if (childrenSumProperty(node.left) && childrenSumProperty(node.right)
					&& ((Integer) node.value == left + right))
				return true;
			else
				return false;
		}
	}
	
	/**
	 * 10.
	 */
	public void convertTreeToChildSum(){
		System.out.println("New Root is = "+_convertTreeToChildSum(this.root));
	}
	
	@SuppressWarnings("unchecked")
	private int _convertTreeToChildSum(Node node){
		if(node==null || (node.left==null && node.right==null)) return 0;
		else{
			_convertTreeToChildSum(node.left);
			_convertTreeToChildSum(node.right);
			Integer lVal=0,rVal=0;
			if(node.left!=null) lVal=(Integer)node.left.value;
			if(node.right!=null) rVal=(Integer)node.right.value;
			//Integers ONLY! [ I know what I'm doing ]
			node.resetValue(new Integer(lVal+rVal),(Class<Value>) Integer.class);
			return (Integer)node.value;
		}
	}
	
	/**
	 * 11.
	 * Diameter in linear time
	 */
	public void getEfficientDiameter(){
		System.out.println("Diamater of tree = "+_getEfficientDiameter(this.root)[0]);
	}
	
	private int[] _getEfficientDiameter(Node node){
		//data[0]=diameter, data[1]=height of cur node
		int[] data = new int[]{0,0};
		if(node==null) return data;
		//leaf node
		if(node.left==null && node.right==null) {
			data[0]=1;data[1]=1;
			return data;
		}
		else{
			int[] lData = _getEfficientDiameter(node.left);
			int[] rData = _getEfficientDiameter(node.right);
			data[1] = Math.max(lData[1], rData[1])+1; //set height of cur node
			data[0]=Math.max(lData[1]+1+rData[1],Math.max(lData[0], rData[0])); //set diameter of cur node
			return data;
		}
	}
	
	/**
	 * 12.
	 * Linear time
	 * @param root
	 */
	public boolean isHeightBalanced(){
		return (isHeightBalanced(root)[0]==0)?true:false;
	}
	
	private int[] isHeightBalanced(Node node){
		//data[0]:0 if true, 1 if false. data[1]=height of cur node
		int[] data = new int[]{0,0};
		if(node==null) return data;
		else{
			int[] lData = isHeightBalanced(node.left);
			int[] rData = isHeightBalanced(node.right);
			data[1] = Math.max(lData[1], rData[1])+1;
			data[0] = (Math.abs(lData[1]-rData[1])>1?1:0)|lData[0]|rData[0];
			return data;
		}
	}
	
	/**
	 * 14.
	 * @param node
	 * @param target
	 * @return
	 */
	public boolean rootToLeafSum(Node node, int target){
		if(node==null) return (target==0);
		if (target==0) return true;
		else{
			return rootToLeafSum(node.left, target-(Integer)node.value) || 
					rootToLeafSum(node.right, target-(Integer)node.value);
		}
	}
	
	/**
	 * 15.
	 * Given pre-order and in-order, construct the binary tree
	 * Overall, O(n^2)
	 * @param preorder
	 * @param inorder
	 */
	public void constructTreeFromPreAndIn(List<Integer> preorder, List<Integer> inorder){
		int[] cur = new int[]{0};
		Node root = _constructTreeFromPreAndIn(preorder, inorder, cur);
		inOrder(root);
		System.out.println();
	}

	private Node _constructTreeFromPreAndIn(List<Integer> preorder, List<Integer> inorder, int[] cur) {
		if (inorder.size() == 0)
			return null;
		else {
			int value = preorder.get(cur[0]);
			cur[0] += 1;
			//this takes linear time
			int splitPt = inorder.indexOf(value);
			Node root = new Node(value);
			//sublist is constant time
			root.left = _constructTreeFromPreAndIn(preorder, inorder.subList(0, splitPt), cur);
			root.right = _constructTreeFromPreAndIn(preorder, inorder.subList(splitPt + 1, inorder.size()), cur);
			return root;
		}
	}
	
	/**
	 * 16.
	 * @param node
	 */
	public void doubleTree(Node node){
		if(node==null) return;
		else{
			//duplicates nodes in a pre-order fashion
			Node _tmp = node.left;
			Node newNode = new Node((Integer)node.value);
			node.left=newNode;
			newNode.left=_tmp;
			doubleTree(node.left.left);
			doubleTree(node.right);
		}
	}
	
	public void maximumWidth(){
		int[] arr = new int[20];
		_maximumWidth(root, 0, arr);
		int max=1;
		for(int i:arr)
			if(i>max)
				max=i;
		System.out.println("Maximum Width = "+max);
	}
	
	private void _maximumWidth(Node node, int assigned, int[] arr){
		if(node!=null){
			arr[assigned]++;
			_maximumWidth(node.left, assigned+1, arr);
			_maximumWidth(node.right, assigned+1, arr);
		}
	}
	
	public Node getNode(Key k, Value v){
		return new Node(k,v,1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<Integer, Integer>();
//		List<Integer> pre = new ArrayList<Integer>(Arrays.asList(new Integer[]{11,7,6,1,4,3,2}));
//		List<Integer> in = new ArrayList<Integer>(Arrays.asList(new Integer[]{6,7,1,4,3,11,2}));
//		bst.constructTreeFromPreAndIn(pre, in);
//		bst.insert(27, 27);
//		bst.insert(8, 8);
//		bst.insert(23, 23);
//		bst.insert(25, 25);
//		bst.insert(6, 6);
//		bst.insert(20, 20);
//		bst.insert(2, 2);
//		bst.insert(31, 31);
//		bst.insert(7, 7);
		bst.insert(11,11);
		
		bst.root.left= bst.getNode(7, 7);
		bst.root.right= bst.getNode(2, 2);
		bst.root.left.left= bst.getNode(6, 6);
		bst.root.left.right= bst.getNode(1,1);
		bst.root.left.right.right= bst.getNode(4, 4);
//		bst.root.left.right.right.right= bst.getNode(3, 3);
		
		bst.maximumWidth();
//		bst.doubleTree(bst.root);
//		bst.inOrder(bst.root);
		System.out.println("Does sum to : "+bst.rootToLeafSum(bst.root, 23));
		
		System.out.println("Is height balanced : "+bst.isHeightBalanced());
		bst.getEfficientDiameter();
		
//		bst.convertTreeToChildSum();
		
		System.out.println(bst.childrenSumProperty(bst.root));
		bst.levelOrderSpiral();
		System.out.println(bst.isBst());
		//Note: Destroys the tree
//		bst.convertTreeToDLL(bst.root);
		
//		bst.lowestCommonAncestor(112, 210);
		
//		bst.printLevelOrder();
//		bst.printRootToLeafPaths(bst.root);
//		bst.mirrorTree(bst.root);
//		System.out.println("Mirrored:");`
//		bst.printLevelOrder();
		
//		BinarySearchTree<Integer, Integer> bst2 = new BinarySearchTree<Integer, Integer>();
//		bst2.insert(27, 27);bst2.insert(8, 8);bst2.insert(23, 23);bst2.insert(25, 25);bst2.insert(6, 6);bst2.insert(20, 20);bst2.insert(2, 2);bst2.insert(31, 31);bst2.insert(7, 7);
//		bst2.insert(11, 11);
//		System.out.println(bst.isIdentical(bst.root, bst2.root));
		
//		System.out.println("Size of tree = "+bst.sizeOfTree(bst.root));		
//		System.out.println(bst.search(311));
//		System.out.println(bst.floor(322));
//		System.out.println(bst.ceil(29));
//		System.out.println("Rank = "+bst.rank(6));
//		System.out.println("Height = "+bst.height());
//		System.out.println("Diameter = "+bst.diameter());
//		System.out.println("LCA = "+bst.lca(1, 111));
//		System.out.println("Level Order  Traversal: ");
//		bst.printLevelOrder();
//		bst.delete(23);
//		System.out.println("Level Order  Traversal: ");
//		bst.printLevelOrder();
//		System.out.println("IsBST? : "+bst.isBST());
////		bst.printTree();	
		
	}
}
