import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 구 ) 원숭이 동작수의 최솟값 (시작점 => 도착점), 못 가면 -1
* K에 따라서 경로가 변하기 때문에
* */
public class Main {
    private static int W,H;
    private static int[][] map;
    private static int[][][] count;
    private static int[] dx = new int[] {-1,0,1,0};
    private static int[] dy = new int[] {0,-1,0,1};
    private static int[] hx = new int[] {-1, -2, -2, -1, 1, 2, 2, 1};
    private static int[] hy = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken()); // 가로길이 5
        H = Integer.parseInt(st.nextToken()); // 세로 길이 2

        map = new int[H][W];
        count = new int[H][W][K + 1];

        for(int i = 0; i < H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = bfs(0,0, K);


        System.out.println(result);

    }

    private static int bfs(int x, int y, int K) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y, K});
        count[x][y][K] = 1;
        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            int tempX = temp[0];
            int tempY = temp[1];
            int tempK = temp[2];

            if(tempX == H-1 && tempY == W-1){ // 도달
                return count[H-1][W-1][tempK] - 1;
            }

            if(tempK > 0) { // 8방 탐색
                for (int i = 0; i < 8; i++) {
                    int nx = tempX + hx[i];
                    int ny = tempY + hy[i];
                    if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
                        if (map[nx][ny] != 1 && count[nx][ny][tempK - 1] == 0) {
                            count[nx][ny][tempK - 1] = count[tempX][tempY][tempK] + 1;
                            queue.add(new int[]{nx, ny, tempK - 1});
                        }
                    }
                }
            }

            for(int i = 0; i < 4; i++) {
                int nx = tempX + dx[i];
                int ny = tempY + dy[i];
                if(nx >= 0 && nx < H && ny >= 0 && ny < W) {
                    if(map[nx][ny] != 1 && count[nx][ny][tempK] == 0) {
                        count[nx][ny][tempK] = count[tempX][tempY][tempK] + 1;
                        queue.add(new int[]{nx, ny, tempK});
                    }
                }
            }
        }
        return -1;
    }
}
