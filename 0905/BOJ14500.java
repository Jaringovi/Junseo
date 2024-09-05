import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N,M;
    private static int[][] paper;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static boolean[][] visited;
    private static int max;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];
        visited = new boolean[N][M];
        max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                makePuzzle(0,i,j,0);
                visited[i][j] = false;
                sadPuzzle(0,i,j,paper[i][j], 0);
            }
        }

        System.out.println(max);
    }

    private static void makePuzzle(int count, int x , int y, int tempSum){
        if(count == 4){
            max = Math.max(max, tempSum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            if(!visited[nx][ny]){
                visited[nx][ny] = true;
                makePuzzle(count+1,nx,ny,tempSum + paper[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    private static void sadPuzzle(int count, int x , int y, int tempSum, int start){
        if(count == 3){
            max = Math.max(max, tempSum);
            return;
        }
        for (int i = start; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            sadPuzzle(count+1,x,y,tempSum + paper[nx][ny], i + 1);
        }
    }

}
