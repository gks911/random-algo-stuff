package edu.gks.random;

public class BinarySearchTreeII {

	private static class TreeNode {
		TreeNode left;
		TreeNode right;
		TreeNode next;
		int value;

		public TreeNode(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value + "";
		}
	}

	public TreeNode createTree() {
		TreeNode root = new TreeNode(50);
		// root.left=new TreeNode(15);
		// root.right=new TreeNode(25);
		// root.left.left=new TreeNode(10);
		// root.left.right=new TreeNode(18);
		// root.left.left.left=new TreeNode(8);
		// root.left.left.right=new TreeNode(12);
		// root.right.left=new TreeNode(23);
		// root.right.right=new TreeNode(30);
		// root.right.right.left=new TreeNode(28);
		// root.right.right.right=new TreeNode(33);

		root.left = new TreeNode(10);
		root.right = new TreeNode(60);
		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(20);
		root.right.left = new TreeNode(55);
		root.right.left.left = new TreeNode(45);
		root.right.right = new TreeNode(70);
		root.right.right.left = new TreeNode(65);
		root.right.right.right = new TreeNode(80);

		// root.left=new TreeNode(1);
		// root.right=new TreeNode(20);
		// root.right.left=new TreeNode(19);
		// root.right.right=new TreeNode(21);

		root.next = null;
		return root;
	}

	/**
	 * 23.
	 * 
	 * @param node
	 * @return
	 */
	public void connectNodesAtSameLevel(TreeNode node) {
		if (node == null)
			return;
		// first fill out the right subtree completely
		if (node.next != null)
			connectNodesAtSameLevel(node.next);

		if (node.left != null) {
			if (node.right != null) {
				node.left.next = node.right;
				node.right.next = getNextNode(node);
			} else
				node.left.next = getNextNode(node);
			connectNodesAtSameLevel(node.left);
		} else if (node.right != null) {
			node.right.next = getNextNode(node);
			connectNodesAtSameLevel(node.right);
		} else
			connectNodesAtSameLevel(node.next);
	}

	private TreeNode getNextNode(TreeNode node) {
		TreeNode tmp = node.next;
		while (tmp != null) {
			if (tmp.left != null)
				return tmp.left;
			if (tmp.right != null)
				return tmp.right;
			tmp = tmp.next;
		}
		return null;
	}

	/**
	 * Efficient, no recursion, constant space.
	 * @param root
	 */
	public void levelOrderConnectNodes(TreeNode root){
		while(root!=null){
			TreeNode _tmp = new TreeNode(0);
			TreeNode _cur = _tmp;
			while(root!=null){
				if(root.left!=null){
					_cur.next=root.left;
					_cur=_cur.next;
				}
				if(root.right!=null){
					_cur.next=root.right;
					_cur=_cur.next;
				}
				root=root.next;
			}
			root=_tmp.next;
		}
	}
	
	/**
	 * A less than "perfect" method to verify results
	 * 
	 * @param root
	 */
	public void printTreeByLevel(TreeNode root) {
		while (root != null) {
			TreeNode tmp = root;
			while (tmp != null) {
				System.out.print(tmp.value + " ");
				tmp = tmp.next;
			}
			System.out.println();
			if (root.left == null)
				root = root.right;
			else
				root = root.left;
		}
	}

	/**
	 * 
	 * @param node
	 */
	static TreeNode prev = null;

	public void populateInorderSuccessor(TreeNode node) {
		if (node != null) {
			populateInorderSuccessor(node.right);
			if (prev != null)
				node.next = prev;
			prev = node;
			populateInorderSuccessor(node.left);
		}
	}

	/**
	 * 25.
	 * Find the largest BST in a given tree.
	 * Complexity = O(n)
	 * I feel this is a very sexy solution!
	 * @param node
	 * @return
	 */
	public int[] largestBst(TreeNode node) {
		// data[0]=largest size, data[1]=min in ltree, data[2]=max in rtree,
		// data[3]=node val
		int[] data = new int[] { 0, Integer.MAX_VALUE, Integer.MIN_VALUE, -1 };
		if (node == null)
			return data;
		if (node.left == null && node.right == null) {
			data[0] = 1;
			data[3] = node.value;
			return data;
		} else {
			int[] l = largestBst(node.left);
			int[] r = largestBst(node.right);

			data[1] = Math.min(node.value, Math.min(l[3], l[1]));
			data[2] = Math.max(node.value, Math.max(r[3], r[2]));
			data[3] = node.value;

			int cur = 0;
			// if node value is greater than max from left subtree
			// and lesser than min from right subtree
			// we have a valid BST
			if (node.value > l[2] && node.value < r[1])
				cur = l[0] + r[0] + 1;
			data[0] = Math.max(Math.max(l[0], r[0]), cur);

			return data;
		}
	}

	/**
	 * 26.
	 * @param node
	 */
	public void boundaryTraversal(TreeNode node){
		if (node!=null)
			printLeftBorder(node);
		
		printLeaves(node);
		
		if (node!=null)
			printRightBorder(node.right);
		
	}
	
	private void printLeftBorder(TreeNode node){
		//does not print the smallest element
		while(node.left!=null){
			System.out.println(node.value);
			node=node.left;
		}
	}
	
	private void printLeaves(TreeNode node){
		if(node==null) return;
		if(node.left==null && node.right==null)
			System.out.println(node.value);
		else{
			printLeaves(node.left);
			printLeaves(node.right);
		}
	}
	
	private void printRightBorder(TreeNode node){
		if(node!=null){
			printRightBorder(node.right);
			//print only when not the leaf
			if(node.left!=null && node.right!=null)
				System.out.println(node.value);
		}
	}
	
	
	public static void main(String[] args) {
		BinarySearchTreeII bst = new BinarySearchTreeII();
		TreeNode root = bst.createTree();
//		System.out.println(bst.largestBst(root)[0]);
		
		bst.boundaryTraversal(root);
//		bst.levelOrderConnectNodes(root);

//		 bst.connectNodesAtSameLevel(root);
//		 bst.printTreeByLevel(root);
		// TreeNode prev=new TreeNode();
		// bst.populateInorderSuccessor(root);
		// prev=root.left.left.left;
		// while(prev!=null){
		// System.out.println(prev.value);
		// prev=prev.next;
		// }
	}

}
