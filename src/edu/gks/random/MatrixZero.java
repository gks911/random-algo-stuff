/**
 * 
 */
package edu.gks.random;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gaurav
 *
 */
public class MatrixZero {

	public static void turnZeros(int[][] matrix, int m, int n){
		Set<Integer> rows=new HashSet<Integer>();
		Set<Integer> column=new HashSet<Integer>();
		
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(matrix[i][j]==0){
					rows.add(i);
					column.add(j);
				}
			}
		}
		
		for(int i:rows){
			for (int j=0;j<n;j++)
				matrix[i][j]=0;
		}
		
		for(int i:column){
			for (int j=0;j<m;j++)
				matrix[j][i]=0;
		}
	}
	
	public static void printMatrix(int[][] matrix, int m, int n){
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
		
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] matrix={{1,0,3},
								{0,5,6},
								{7,8,9}};
		
		printMatrix(matrix, 3, 3);
		turnZeros(matrix, 3, 3);
		printMatrix(matrix, 3, 3);
		
	}

}
