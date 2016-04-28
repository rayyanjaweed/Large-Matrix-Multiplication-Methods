/*
 * File Name : DynamicScheduling.java
 * Short Description : This java code does matrix multiplication by dynamic scheduling method
 * Version Number : 1.0
 * Created Date : 04/24/2016
 */


/**
 * This class has method which does matrix multiplication by dynamic scheduling method
 * 
 * @author Rayyan Mohammed Jaweed
 * @version 1.0
 */
public class DynamicScheduling implements Runnable{
	
	static int C[][];
	static int n;
	static int cores;
	static int A[][];
	static int B[][];
	static int row;
	int index;
	
	private static final Object countLock = new Object();
	
	/**
	 * Constructor used while creating a new thread
     */
	public DynamicScheduling(int n){
		index = 0;
	}
	
	/**
	 * Constructor used to initialize A and B matrices for multiplication and the noOfCores
     */
	public DynamicScheduling(int[][] A,int[][]B,int noOfCores){
		this.A = A;
		this.B = B;
		this.n = A.length;
		this.cores = noOfCores;
		C = new int[n][n];
		this.row = 0;
	}
	
	public synchronized void incrementRow(){
		synchronized (countLock) {
			this.index = row;
			row++;
        }
	}

	@Override
	public void run() {
		while(row<n){
			incrementRow();
			if(index < n){
				for (int j = 0; j < n; j++){
					for (int k = 0; k < n; k++){
						this.C[index][j] += A[index][k] * B[k][j];
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
			myThreads[i] = new Thread(new DynamicScheduling(i));
			myThreads[i].start();
		}
		for(int j=0 ; j<noOfThreads ; j++){
			myThreads[j].join();
		}
		return this.C;
	}
}
