/**
 * 
 */
package edu.gks.random;

/**
 * @author gaurav
 *
 */
public class CEval {

	private static class Line{
		double x1;
		double y1;
		double x2;
		double y2;
		double m;
		double c;
		
		public Line(double x1, double y1, double x2, double y2){
			this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
			this.m=setSlope();
			this.c=setIntercept();
		}
	
		private double setSlope(){
			double slope=0;
			try{
				slope=(y2-y1)/(x2-x1);
			}catch(Exception e){
				System.err.println("Slope is 0!");
				e.printStackTrace();
			}
			return slope;
		}
		
		private double setIntercept(){
			return y1-m*x1;
		}
		
		public boolean doesIntersect(Line l){
			double tmp1=Math.signum((l.x1-x1)*(y2-y1)-(l.y1-y1)*(x2-x1));
			double tmp2=Math.signum((l.x2-x1)*(y2-y1)-(l.y2-y1)*(x2-x1));
			if((tmp1<0 && tmp2<0) || (tmp1>0 && tmp2>0))
				//on the same side
				return false;
			return true;
			
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * 	1: [37.788353, -122.387695], [37.829853, -122.294312]
		   	2: [37.429615, -122.087631], [37.487391, -122.018967]
		   	3: ([37.474858, -122.131577], [37.529332, -122.056046])
			4: ([37.532599,-122.218094], [37.615863,-122.097244])
			5: ([37.516262,-122.198181], [37.653383,-122.151489])
			6: ([37.504824,-122.181702], [37.633266,-122.121964])
		 */
			
		Line l1 = new Line(37.788353, -122.387695,37.829853, -122.294312);
		Line l2 = new Line(37.429615, -122.087631,37.487391, -122.018967);
		Line l3 = new Line(37.474858, -122.131577,37.529332, -122.056046);
		Line l4 = new Line(37.532599,-122.218094,37.615863,-122.097244);
		Line l5 = new Line(37.516262,-122.198181,37.653383,-122.151489);
		Line l6 = new Line(37.504824,-122.181702,37.633266,-122.121964);
		
		
		System.out.println(l1.doesIntersect(l1));
		System.out.println(l1.doesIntersect(l2));
		System.out.println(l1.doesIntersect(l3));
		System.out.println(l1.doesIntersect(l4));
		System.out.println(l1.doesIntersect(l5));
		System.out.println(l1.doesIntersect(l6));
		
	}

}
