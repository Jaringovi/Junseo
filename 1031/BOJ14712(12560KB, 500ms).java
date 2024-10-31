import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static boolean[][] game;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        game = new boolean[N][M];

        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int depth) {
        // 깊이가 N * M에 도달하면 탐색을 종료
        if (depth == N * M) {
            answer++;
            return;
        }

        int row = depth / M;
        int col = depth % M;

        // 현재 위치에 블록을 놓지 않고 다음 depth로 이동
        dfs(depth + 1);

        // 현재 위치에 블록을 놓을 경우 2x2 정사각형이 만들어지지 않는지 확인
        if (!(row > 0 && col > 0 && game[row - 1][col] && game[row][col - 1] && game[row - 1][col - 1])) {
            game[row][col] = true; // 현재 위치에 블록을 놓고 탐색
            dfs(depth + 1);
            game[row][col] = false; // 탐색 후 블록을 제거
        }
    }
}
