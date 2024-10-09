import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static int[][] graph;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		graph = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1; i < N; i++) {
			graph[i][0] = Math.min(graph[i-1][1], graph[i-1][2]) + graph[i][0];
			graph[i][1] = Math.min(graph[i-1][0], graph[i-1][2]) + graph[i][1];
			graph[i][2] = Math.min(graph[i-1][0], graph[i-1][1]) + graph[i][2];
		}
		System.out.println(Math.min(graph[N-1][0], Math.min(graph[N-1][1], graph[N-1][2])));
	}

}
