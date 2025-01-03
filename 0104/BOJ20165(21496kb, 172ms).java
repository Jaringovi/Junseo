import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 각 격자판의 높이가 자신을 포함하여 몇칸이나 쓰러질 수 있는지 적혀 있는 것이다. (포함 주의)
* 동,서,남,북 중 원하는 방향으로 넘어 뜨린것. 연쇄적으로 도미노 넘어지기 가능. 연쇄 시 적혀있는 숫자로 또 연쇄 하는거 있지말기. 무조건 다 연쇄 되는거 아님
* 각 라운드 별 기록 받고, 마지막 최종 게임판 상태 출력
* */
public class Main {
    private static int N,M,R; // 행 , 열, 라운드
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] domino;
    private static char[][] visited;
    private static int[] dx = new int[] {-1, 0, 1, 0}; // 북 동 남 서
    private static int[] dy = new int[] {0, 1, 0, -1};
    private static int attackCount;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        domino = new int[N][M];
        visited = new char[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = 'S';
            }
        }

        attackCount = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                domino[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()) - 1;
            int Y = Integer.parseInt(st.nextToken()) - 1;
            char D = st.nextToken().charAt(0);
            st = new StringTokenizer(br.readLine());
            int dX = Integer.parseInt(st.nextToken()) - 1;
            int dY = Integer.parseInt(st.nextToken()) - 1;

            attack(X,Y,D);
            defense(dX, dY);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(attackCount).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(visited[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // 도미노 방어 함수
    private static void defense(int X, int Y) {
        if(visited[X][Y] == 'F') {
            visited[X][Y] = 'S';
        }
    }

    // 도미노 공격 할 함수 => 연쇄 작용 신경 쓰기
    private static void attack(int X, int Y, char D) { // 공격할 때 마다 호출 될 거임
        int dir = makeDir(D);
        visited[X][Y] = 'F';
        attackCount++; // 어차피 해당 칸은 무조건 공격이니까
        int height = domino[X][Y]; // Height 갱신해주면서 연쇄 작용 해야 한다.
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{X, Y, height});

        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            int dxDir = temp[0];
            int dyDir = temp[1];
            int newHeight = temp[2];

            for(int i = 0; i < newHeight - 1; i++) {
                dxDir += dx[dir];
                dyDir += dy[dir];
                if(dxDir >= 0 && dxDir < N && dyDir >= 0 && dyDir < M && visited[dxDir][dyDir] == 'S') { // 범위를 넘어서지 않고, 방문 X
                    attackCount++;
                    visited[dxDir][dyDir] = 'F';
                    queue.add(new int[] {dxDir, dyDir, domino[dxDir][dyDir]});
                }
            }
        }

    }

    // 방향 return 함수
    private static int makeDir(char D) {
        int dir = 0;
        if(D == 'E') {
            dir = 1;
        } else if (D == 'W') {
            dir = 3;
        } else if (D == 'S') {
            dir = 2;
        } else if(D == 'N') {
            dir = 0;
        }
        return dir;
    }
}
