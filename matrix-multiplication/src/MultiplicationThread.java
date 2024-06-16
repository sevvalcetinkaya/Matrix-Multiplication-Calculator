import java.util.Queue;

public class MultiplicationThread implements Runnable {
	int Task, colmA;
	Queue<MultiplicationItem> q;
	Thread T;
	
	public MultiplicationThread(Queue<MultiplicationItem> q, int Task, int colA) {
		this.q = q;
		this.Task = Task;
		T = new Thread(this);
		colmA = colA;
		T.start();
	}
	
	public void run() {
		int time = 0;
		while(!q.isEmpty() && time < Task) {
			synchronized (q) {
				
				int i, sum = 0;
				MultiplicationItem Item = q.remove() ; 
				for(i = 0; i <= colmA; i++) {
					sum += (Item.arrA[i] * Item.arrB[i]);
				}
				Main.Res[Item.i][Item.j] = sum;
				time++;
			}
		}
	}

}
