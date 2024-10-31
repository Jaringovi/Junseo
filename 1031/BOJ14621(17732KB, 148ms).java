import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static class Edge implements Comparable<Edge>{
		int start, end, distance;

		public Edge(int start, int end, int distance) {
			super();
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return this.distance - o.distance;
		}
		
	}
	
	private static void makeSet() {
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int v) {
		if(parents[v] == v) return v;
		return parents[v] = find(parents[v]);
	}
	
	private static boolean union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA == parentB) return false;
		if (parentA > parentB) {
			parents[parentA] = parentB;
		} else {
			parents[parentB] = parentA;
		}
		return true;
	}
	
	private static int N,M;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static PriorityQueue<Edge> edges = new PriorityQueue<>();
	private static int[] parents;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		String[] gender = new String[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
		    gender[i] = st.nextToken();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			edges.add(new Edge(u, v, dis));
		}
		
		parents = new int[N + 1];
		makeSet();
		
		int count = 0, cost = 0;
		while(!edges.isEmpty()) {
			Edge edge = edges.poll();
			if(!gender[edge.start].equals(gender[edge.end])) {
				if(union(edge.start, edge.end)) {
					cost += edge.distance;
					count++;
					if(count == N - 1) break;
				}
			}
		}
		
		if(count == N-1) {
			System.out.println(cost);
		} else {
			System.out.println(-1);
		}
	}
}
