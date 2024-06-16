import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] Res = new int[1000][1000];
	public static void main(String[] args) {
		Queue<MultiplicationItem> q;
		q = new LinkedList<>();
		
		int rowA, rowB;
		int columnA, columnB;
		int n;
		int[][] A, B, TransB;
		A = new int[1000][1000];
		B = new int[1000][1000];
		TransB = new int[1000][1000];
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Row Number of Matrix A: ");
		rowA = input.nextInt();
		System.out.println("Enter Column Number of Matrix A: ");
		columnA = input.nextInt();
		System.out.println("Enter Row Number of Matrix B: ");
		rowB = input.nextInt();
		System.out.println("Enter Column Number of Matrix B: ");
		columnB = input.nextInt();
		int i, j;
		
		System.out.println("Enter Elements of Matrix A: ");
		for(i = 0; i < rowA; i++) {
			for(j = 0; j < columnA; j++) {
				A[i][j] = input.nextInt();
			}
		}
		System.out.println("Enter Elements of Martix B: ");
		for(i = 0; i < rowB; i++) {
			for(j = 0; j < columnB; j++) {
				B[i][j] = input.nextInt();
			}
		}
		System.out.println("How Many Threads Do You Want to Create?: ");
		n = input.nextInt();
		
		for(i = 0; i < rowB; i++) {
			for(j = 0; j < columnB; j++) {
				TransB[j][i] = B[i][j];
			}
		}
		
		for(i = 0; i < rowA; i++) {
			for(j = 0; j < columnB; j++) {
				MultiplicationItem Item = new MultiplicationItem(i, j, A[i], TransB[j]);
				q.add(Item);
			}
		}
		if(columnA == rowB) {
		int TaskThread = (rowA * columnB) / n;
		MultiplicationThread[] t = new MultiplicationThread[n];
		for(i = 0; i < n; i++) {
			t[i] = new MultiplicationThread(q, TaskThread, columnA);
		}
		try {
			for(i = 0; i < n; i++) {
				t[i].T.join();
			}
		} catch(InterruptedException ex) {
			System.out.println("Interrupted.");
		}
		for(i = 0; i < rowA; i++) {
			for(j = 0; j < columnB; j++) {
				System.out.println(Res[i][j] + " ");
			}
			System.out.println("");
		}
	} else {
		System.out.println("Girilen Matrisler Çarpım İçin Uygun Değildir.");
	}
}
}
