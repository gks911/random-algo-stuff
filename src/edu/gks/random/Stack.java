/**
 * 
 */
package edu.gks.random;

/**
 * @author gaurav
 *
 */
@SuppressWarnings("unchecked")
public class Stack<T extends Comparable<T>> {
	
	Object[] buffer;
	int top;
	int size;
	
	public Stack(int size){
		this.size=size;
		buffer=new Object[size];
		top=-1;
	}
	
	public void push(T obj){
		if(top!=size-1){
			buffer[++top]=(T)obj;
		}else
			System.err.println("Cannot insert. Stack full.");
	}
	
	public T pop(){
		if(top>=0)
			return (T)buffer[top--];
		else
			System.err.println("Cannot pop. Stack empty.");
		return null;
	}
	
	public T peek(){
		if(top>=0)
			return (T)buffer[top];
		return null;
	}
	
	public boolean isEmpty(){
		return (top<0);
	}
	
	public Stack<Integer> sortStack(Stack<Integer> stack){
		Stack<Integer> _tmp = new Stack<Integer>(stack.size);
		while(!stack.isEmpty()){
			int _tmpPop=stack.pop();
			while(!_tmp.isEmpty() && _tmp.peek()<_tmpPop)
				stack.push(_tmp.pop());
			_tmp.push(_tmpPop);
		}
		return _tmp;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>(10);
		for(int i=0;i<11;i++)
			stack.push(i);
		
		Stack<Integer> sorted = stack.sortStack(stack);
		
		while(!sorted.isEmpty())
			System.out.println(sorted.pop());
	}
}
