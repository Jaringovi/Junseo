import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] game;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static boolean[] visited;
    private static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        game = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                game[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int count, int idx) {
        if(count == N/2) {
            int[] temp = calculate();
            answer = Math.min(answer, Math.abs(temp[0] - temp[1]));
            return;
        }
        for (int i = idx; i < N; i++) {
            visited[i] = true;
            dfs(count + 1, i + 1);
            visited[i] = false;
        }
    }

    private static int[] calculate() {
        int start = 0;
        int link = 0;
        int[] temp = new int[2];
        for(int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                if(visited[i] && visited[j]) {
                   start += game[i][j] + game[j][i];
                } else if(!visited[i] && !visited[j]) {
                    link += game[i][j] + game[j][i];
                }
            }
        }
        temp[0] = start;
        temp[1] = link;
        return temp;
    }
}
