import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Ticket implements Comparable<Ticket>{
		String eng;
		int num;
		
		public Ticket(String eng, int num) {
			super();
			this.eng = eng;
			this.num = num;
		}

		@Override
		public int compareTo(Ticket o) {
			int compareEng = this.eng.compareTo(o.eng);
			if(compareEng != 0) {
				return compareEng;
			} else {
				return this.num - o.num;
			}
		}
	}
	
	private static int N;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static String[][] waiting;
	private static PriorityQueue<Ticket> answer = new PriorityQueue<>();
	private static Queue<Ticket> line = new LinkedList<>();
	private static Deque<Ticket> againWait = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		waiting = new String[N][5];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				waiting[i][j] = st.nextToken();
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 5; j++) {
				String[] temp = waiting[i][j].split("-");
				line.add(new Ticket(temp[0], Integer.parseInt(temp[1])));
				answer.add(new Ticket(temp[0], Integer.parseInt(temp[1])));
			}
			
		}
		
		if(check()) {
			System.out.println("GOOD");
		} else {
			System.out.println("BAD");
		}
			
	}
	
	private static boolean check() {
		while(!answer.isEmpty()) {
			Ticket expected = answer.peek();
			
			if(!line.isEmpty() && line.peek().eng.equals(expected.eng) && line.peek().num == expected.num) {
				answer.poll();
				line.poll();
				continue;
			} 
			
			if(!againWait.isEmpty() && againWait.peekLast().eng.equals(expected.eng) && againWait.peekLast().num == expected.num) {
				againWait.pollLast();
				answer.poll();
				continue;
			}
			
			if (line.isEmpty()) {
				return false;
			}
			
			againWait.add(line.poll());

		}
		
		return true;
		
	}

}
