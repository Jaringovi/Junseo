import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int N = 9;
	private static int[][] game = new int[N][N];
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				game[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backtracking(0, 0);
		
	}
	
	
	private static void backtracking(int row, int col) {
		if(row == N) {
			printAnswer();
			System.exit(0);
		}
		
		if(col == N) {
			backtracking(row + 1, 0);
			return;
		}
		
		if(game[row][col] != 0) {
			backtracking(row, col + 1);
			return;
		}
		
		for(int num = 1; num <= N; num++) {
			if(isValid(row, col, num)) {
				game[row][col] = num;
				backtracking(row, col + 1);
				game[row][col] = 0;
			}
		}
	}
	
	private static boolean isValid(int row, int col, int num) {
		for(int i = 0; i < N; i++) {
			if(game[row][i] == num) {
				return false;
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(game[i][col] == num) {
				return false;
			}
		}
		
		// 9개의 사각형 검사 
		int startRow = row / 3 * 3;
		int startCol = col / 3 * 3;
		for(int i = startRow; i < startRow + 3; i++) {
			for(int j = startCol; j < startCol + 3; j++) {
				if(game[i][j] == num) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static void printAnswer() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(game[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
