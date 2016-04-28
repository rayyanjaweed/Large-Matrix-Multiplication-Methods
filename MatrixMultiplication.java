/*
 * File Name : MatrixMultiplication.java
 * Short Description : This java code does basic matrix multiplication
 * Version Number : 1.0
 * Created Date : 04/24/2016
 */


/**
 * This class has method which does basic matrix multiplication
 * 
 * @author Rayyan Mohammed Jaweed
 * @version 1.0
 */
public class MatrixMultiplication{
	
	public int[][] multiplication(int[][] A, int[][]B){
		int m1 = A.length;
        int n1 = A[0].length;
        int m2 = B.length;
        int n2 = B[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
        int[][] C = new int[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                   {
                       C[i][j] += A[i][k] * B[k][j];
                    }
        
        return C;
	}
}
