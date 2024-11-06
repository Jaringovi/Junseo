import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static boolean[][] map;
    private static double[] percent;
    private static int[] dx = {0,0,1,-1};
    private static int[] dy = {1,-1,0,0};
    private static int robotX, robotY;
    private static double answer = 0;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new boolean[30][30];
        percent = new double[4];

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            percent[i] = Integer.parseInt(st.nextToken()) / 100.0;
        }
        backtracking(15,15,0,1.0);
        System.out.println(answer);
    }

    private static void backtracking(int x, int y, int depth, double prob){
        if(map[x][y] == false) {
            if(depth == N) {
                answer += prob;
                return;
            }
        }

        map[x][y] = true;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!map[nx][ny]) {
                backtracking(nx,ny,depth+1,prob*percent[i]);
            }
        }
        map[x][y] = false;
    }
}
