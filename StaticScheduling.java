/*
 * File Name : StaticScheduling.java
 * Short Description : This java code does matrix multiplication by static scheduling method
 * Version Number : 1.0
 * Created Date : 04/24/2016
 */


/**
 * This class has method which does matrix multiplication by static scheduling method
 * 
 * @author Rayyan Mohammed Jaweed
 * @version 1.0
 */
public class StaticScheduling implements Runnable{

	static int C[][];
	static int n;
	static int cores;
	static int A[][];
	static int B[][];
	int x;
	
	/**
	 * Constructor used while creating a new thread
     */
	public StaticScheduling(int x) {
		this.x = x;
	}
	
	/**
	 * Constructor used to initialize A and B matrices for multiplication and the noOfCores
     */
	public StaticScheduling(int A[][],int B[][],int noOfCores){
		this.A = A;
		this.B = B;
		this.n = A.length;
		this.cores = noOfCores;
		C = new int[n][n];
	}
	
	@Override
	public void run() {
		//This loop divides the work load among the different threads depending on the thread variable x and the noOfCores
		for (int i = ((n/cores) *(x-1)); i <= (((n/cores) *x) - 1 ); i++){
			for (int j = 0; j < n; j++){
				for (int k = 0; k < n; k++){
					this.C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		
		//This code is reached only if the size of the matrix n is not evenly split among all th threads
		if(n%cores != 0 && x==cores){
			int remaining = n%cores;
			for(int i= n -remaining ; i<n ; i++){
				for (int j = 0; j < n; j++){
					for (int k = 0; k < n; k++){
						this.C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
	}
	
	/**
     * This method does the multiplication. It creates the different number of threads depending on the noOfCores
     */
	public int[][] multiply() throws InterruptedException{
		int noOfThreads = cores;
		Thread myThreads[] = new Thread[noOfThreads];
		for(int i=0 ; i<noOfThreads ; i++){
			myThreads[i] = new Thread(new StaticScheduling(i+1));
			myThreads[i].start();
		}
		for(int j=0 ; j<noOfThreads ; j++){
			myThreads[j].join();
		}
		return this.C;
	}
}
