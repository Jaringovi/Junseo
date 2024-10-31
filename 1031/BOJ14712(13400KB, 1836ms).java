import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N,M;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static boolean[][] game;
	private static int answer = 0;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		game = new boolean[N][M];
		
		dfs(0, 0);
		System.out.println(answer);
	}
	
	private static void dfs(int depth, int start) {
		if(!isSquare(depth)) answer++;
		if(depth == N * M) {
			return;
		}
		
		for(int i = start; i < N * M; i++) {
			int row = i / M;
			int col = i % M;
			
			if(game[row][col]) continue;
			
			game[row][col] = true;
			dfs(depth + 1, i);
			game[row][col] = false;
		}
	}
	
	
	private static boolean isSquare(int depth) {
		if(depth < 4) {
			return false;
		}
		for(int i = 0; i < N - 1; i++) {
			for(int j = 0; j < M - 1; j++) {
				if(game[i][j] && game[i+1][j] && game[i+1][j+1] && game[i][j+1]) return true;
			}
		}
		
		return false;
	}
}
