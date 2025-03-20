import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 구 ) 인구 이동이 며칠간 발생하는가 ?
* 인구이동이 발동하는 조건 : 나라 간의 인구 차이가 L이상 R이하 => 판별 후 이동할 것이 있으면 +1
* */
public class Main {
    private static int[][] map;
    private static int N,L,R;
    private static int[] dx = new int[]{-1, 0, 1, 0};
    private static int[] dy = new int[]{0, -1, 0, 1};
    private static int answer;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 맵 크기
        L = Integer.parseInt(st.nextToken()); // 차이가 L명 이상
        R = Integer.parseInt(st.nextToken()); // 차이가 R명 이상

        map = new int[N][N];
        answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(isMove()) {
            answer++;
        }

        System.out.println(answer);

    }

    private static List<int[]> bfs(int x, int y, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        List<int[]> union = new ArrayList<>();
        union.add(new int[]{x, y}); // 맨 처음 나도 추가
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int tempX = temp[0];
            int tempY = temp[1];
            for(int i = 0; i < 4; i++) {
                int nx = tempX + dx[i];
                int ny = tempY + dy[i];
                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    if(Math.abs(map[nx][ny] - map[tempX][tempY]) >= L && Math.abs(map[nx][ny] - map[tempX][tempY]) <= R) {
                        q.add(new int[]{nx, ny});
                        union.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return union;
    }

    private static boolean isMove() { // 움직일 수 있는지
        boolean flag = false;
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    List<int[]> union = bfs(i, j, visited);
                    if(union.size() > 1) { // 나 제외
                        flag = true;
                        changePerson(union);
                    }
                }
            }
        }
        return flag;
    }

    private static void changePerson(List<int[]> union) {
        int total = 0;
        for(int[] temp : union) {
            int x = temp[0];
            int y = temp[1];
            total += map[x][y];
        }

        for(int[] temp : union) {
            int x = temp[0];
            int y = temp[1];
            map[x][y] = total / union.size();
        }
    }
}
