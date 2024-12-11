import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N = 19;
    private static int[][] grid;
    private static int[] dx = new int[]{1, -1, 0, 1};
    private static int[] dy = new int[]{0, 1, 1, 1};
    private static boolean isFinish = false;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int j = 0; j < N; j++) {
            for(int i = 0; i < N; i++) {
                if(grid[i][j] != 0 && !isFinish) {
                    int color = grid[i][j];
                    for(int k = 0; k < 4; k++) {
                        dfs(i, j, 1, color, k);
                        if (isFinish) {
                            sb.append(color).append("\n").append(i + 1).append(" ").append(j + 1);
                            System.out.println(sb);
                            return;
                        }
                    }
                }
            }
        }

        if(!isFinish){
            System.out.println(0);
        }

    }

    private static void dfs(int x, int y, int depth, int color, int dir) {
        if (depth == 5) {
            if (isValid(x, y, dir, color)) {
                isFinish = true;
            }
            return;
        }
        if (isFinish) return;

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(nx < 0 || ny < 0 || nx >= N || ny >= N || grid[nx][ny] != color) return;

        dfs(nx, ny, depth + 1, color, dir);

    }

    private static boolean isValid(int x, int y, int dir, int color) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        int oppoNx = x - 5 * dx[dir]; // 반대 => 현재 온 곳 기준 5번째 전으로 가야 한다.
        int oppoNy = y - 5 * dy[dir]; // 반대


        boolean first = nx < 0 || ny < 0 || nx >= N || ny >= N || grid[nx][ny] != color;
        boolean second = oppoNx < 0 || oppoNx >= N || oppoNy < 0 || oppoNy >= N || grid[oppoNx][oppoNy] != color;

        return first && second;
    }
}
