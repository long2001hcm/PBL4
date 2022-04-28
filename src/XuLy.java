import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class XuLy {
	String URLFileA;
	String URLFileB;
	String URLFileKQ;
	public void setURL(String URLFileA, String URLFileB, String URLFileKQ) {
		this.URLFileA = URLFileA;
		this.URLFileB = URLFileB;
		this.URLFileKQ = URLFileKQ;
	}
	public void run() throws Exception {
		int M;
        int K;
        int N;
        double A[][];
        double B[][];
        double C[][];
        
		try {
			BufferedReader readerA = new BufferedReader(new FileReader(URLFileA));
			BufferedReader readerB = new BufferedReader(new FileReader(URLFileB));
			String[] partsA = readerA.readLine().split(" ");
			String[] partsB = readerB.readLine().split(" ");
			M = Integer.parseInt(partsA[0]);
			K = Integer.parseInt(partsA[1]);
			int test = Integer.parseInt(partsB[0]);
			N = Integer.parseInt(partsB[1]);
			//neu cot ma tran A khong bang hang ma tran B thi bao loi
			if (test != K) {
				readerA.close();
				readerB.close();
				throw new java.io.IOException();
			}
			A = new double[M][K];
	        B = new double[K][N];
	        C = new double [M][N];
	        //read A
	        for (int i = 0; i < M; i++) {
	        	String[] parts = readerA.readLine().split(" ");
	        	for (int j = 0; j < K; j++) {
	        		A[i][j] = Double.parseDouble(parts[j]);
	        	}
	        }
	        //read B
	        for (int i = 0; i < K; i++) {
	        	String[] parts = readerB.readLine().split(" ");
	        	for (int j = 0; j < N; j++) {
	        		B[i][j] = Double.parseDouble(parts[j]);
	        	}
	        }
	        
	        //Nhan ma tran
	        ExecutorService pool = Executors.newFixedThreadPool(4);
	        for (int i = 0; i<M; i++){		
	                WorkerThread runnable = new WorkerThread(i, N, A, B, C, K);
	                pool.execute(runnable);
	        }
	        pool.shutdown();
	        while (!pool.isTerminated()) {}
	        
	        //ghi ket qua ra file
	        BufferedWriter writer = new BufferedWriter(new FileWriter(this.URLFileKQ + "\\output.txt"));
	        for (int i = 0; i<M; i++){
	            for (int j=0; j<N; j++){
	                writer.write(C[i][j] + " ");
	            }
	            writer.write("\n");
	        }  
			readerA.close();
			readerB.close();
			writer.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		
	}
}


class WorkerThread implements Runnable{
    private int row;
    private int N;
    private int k;
    private double [][] A;
    private double [][] B;
    private double [][] C;
    
    public WorkerThread(int row, int N, double[][] A, double[][] B, double[][] C, int k) {
        this.row = row;
        this.N = N;
        this.A = A;
        this.B = B;
        this.C = C;
        this.k = k;
    }
    
    public void run() {
    	//tinh 1 hang cua ma tran ket qua
    	for (int j=0; j<N; j++)
    		for (int i = 0; i < k; i++)		
    			C[row][j] += A[row][i] * B[i][j];
    }   
}
