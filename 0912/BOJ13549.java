import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13549 {
	
	static class Node implements Comparable<Node>{
		int next, weight;
		
		public Node(int next, int weight) {
			this.next = next;
			this.weight = weight;
			
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N,K;
	private static int[] dx = {2, -1, 1};
	private static int[] street;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(K == 0) {
			System.out.println(N);
			return;
		}
			
		street = new int[100001];
		
		Arrays.fill(street, 100000);
		
		dijkstra();
		System.out.println(street[K]);
		
	}
	
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(N,0));
		street[N] = 0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			for(int i = 0; i < 3; i++) {
					if(i == 0) {
						int nx = cur.next * dx[i];
						if(nx >= 0 && nx <= 100000 && street[nx] > cur.weight){
							street[nx] = cur.weight;
							pq.add(new Node(nx, cur.weight));
						}
					} else {
						int nx = cur.next+dx[i];
						if(nx >= 0 && nx <= 100000 && street[nx] > cur.weight + 1){
							street[nx] = cur.weight + 1;
							pq.add(new Node(nx,cur.weight + 1));
						}
						
				}
				
			}
		}
	}
	
}
