import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] commands;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] grid = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		commands = new int[R];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < R; i++) {
			commands[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < R; i++) {
			if(commands[i] == 1) {
				grid = one(grid);
			} else if(commands[i] == 2) {
				grid = two(grid);
			} else if(commands[i] == 3) {
				grid = three(grid);
			} else if(commands[i] == 4) {
				grid = four(grid);
			} else if(commands[i] == 5) {
				grid = five(grid);
			} else if(commands[i] == 6) {
				grid = six(grid);
			}
		}
		
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	private static int[][] one(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		for(int i = 0; i < N / 2; i++) {
			int[] temp = grid[i];
			grid[i] = grid[N-i-1];
			grid[N-i-1] = temp;
		}
		return grid;
	}
	
	private static int[][] two(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M / 2; j++) {
				int temp = grid[i][j];
				grid[i][j] = grid[i][M-j-1];
				grid[i][M-j-1] = temp;
			}
		}
		return grid;
	}
	
	private static int[][] three(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		int[][] newGrid = new int[M][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				newGrid[j][N-i-1] = grid[i][j]; 
			}
		}
		return newGrid;
	}
	
	private static int[][] four(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		int[][] right90 = three(grid);
		int[][] operationTwo = two(right90);
		int[][] operationOne = one(operationTwo);
		return operationOne;
	}
	
	private static int[][] five(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		int[][] newGrid = new int[N][M];
		for(int i = 0; i < N / 2; i++) {
			for(int j = 0; j < M / 2; j++) {
				newGrid[i][j + M / 2] = grid[i][j];
				newGrid[i + N / 2][j + M / 2] = grid[i][j + M / 2];
				newGrid[i + N / 2][j] = grid[i + N / 2][j + M / 2];
				newGrid[i][j] = grid[i + N / 2][j];
			}
		}
		return newGrid;
	}
	
	private static int[][] six(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		int[][] newGrid = new int[N][M];
		for(int i=0; i < N / 2; i++) {
			for(int j = 0; j < M / 2; j++) {
				newGrid[i][j] = grid[i][j + M / 2];
				newGrid[i][j + M / 2] = grid[i + N / 2][j + M / 2];
				newGrid[i + N / 2][j] = grid[i][j];
				newGrid[i + N / 2][j + M / 2] = grid[i + N / 2][j];
			}
		}
		return newGrid;
	}
}
