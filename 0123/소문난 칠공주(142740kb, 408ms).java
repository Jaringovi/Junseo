import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
* 총 25명, 5 X 5 정사각형 격자.
*
* 이름이 이름인 만큼. 7명의 여학생들로 구성되어야 한다/
* 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해있어야 한다.
* 화합과 번영을 위해, 반드시 '이다솜파'의 학생들로만 구성될 필요는 없다.
* 그러나 생존을 위해, '이다솜파'가 반드시 우위를 점해야 한다. 따라서 7명의 학생 중 '이다솜 파'의
* 학생이 적어도 4명이상은 반드시 포함되어야 한다.
*
* S : 이다솜파 => 적어도 4명 이상
* Y : 임도연파 => 4명 부터 안됨.
*
* 구 ) 격자 판 에서 위의 조건을 만족하며 소문난 칠공주를 결성할 수 있는 모든 경우의 수 출력
*
* 전략 : 좌표 걍 7개 냅다 뽑고, 연결되어 있는지 확인하자
*
* 삽질 ) contains는 객체의 주소값을 비교해서 new int[] {nx, ny} 같은
* 임시값은 비교가 안된다
* */
public class Main {
    private static int N = 5;
    private static char[][] classroom;
    private static List<int[]> selected;
    private static int[] dx = new int[] {-1,1,0,0};
    private static int[] dy = new int[] {0,0,-1,1};
    private static int answer;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        classroom = new char[N][N];
        selected = new ArrayList<>();
        answer = 0;

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < N; j++) {
                classroom[i][j] = s.charAt(j);
            }
        }


        dfs(0,0,0);
        System.out.println(answer);
    }

    private static void dfs(int depth, int countY, int start) { // 조합이라서 중복 없음
        if(countY >= 4) {
            return;
        }
        if(depth == 7) {
            bfs();
            return;
        }

        for(int i = start; i < N * N; i++) {
            int x = i / N;
            int y = i % N;

            int temp = countY;
            if(classroom[x][y] == 'Y') {
                temp++;
            }
            if(temp < 4) {
                selected.add(new int[]{x, y});
                dfs(depth + 1, temp, i + 1);
                selected.remove(selected.size() - 1);
            }
        }
    }

    private static void bfs() { // 골라진 좌표들이 다들 연결되어 있는지 확인하기
        int count = 1;
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        int[] start = selected.get(0);
        int x = start[0];
        int y = start[1];
        visited[x][y] = true;
        q.add(start);
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            for(int d = 0; d < 4; d++) {
                int nx = curX + dx[d];
                int ny = curY + dy[d];
                if(0 <= nx && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    if(isEquals(nx, ny)) {
                        count++;
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }

        if(count == 7) {
            answer++;
        }
    }

    private static boolean isEquals(int nx, int ny) {
        for(int[] select : selected) {
            if(nx == select[0] && ny == select[1]) {
                return true;
            }
        }
        return false;
    }
}
