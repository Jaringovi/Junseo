import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static LinkedList<Integer>[] system = new LinkedList[4]; // 4개의 톱니바퀴 시스템
    static boolean[] visited; // 톱니바퀴 방문 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 톱니바퀴 입력 받기
        for (int i = 0; i < 4; i++) {
            system[i] = new LinkedList<>();
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                system[i].add(input.charAt(j) - '0'); // 문자열을 숫자로 변환해서 저장
            }
        }

        int K = Integer.parseInt(br.readLine()); // 회전 횟수 입력
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; // 회전시킬 톱니바퀴 번호 (0-based)
            int b = Integer.parseInt(st.nextToken());     // 회전 방향 (1: 시계, -1: 반시계)
            visited = new boolean[4];
            moveSystem(a, b);
        }

        int answer = 0;
        if (system[0].get(0) == 1) answer += 1;
        if (system[1].get(0) == 1) answer += 2;
        if (system[2].get(0) == 1) answer += 4;
        if (system[3].get(0) == 1) answer += 8;

        System.out.println(answer);
    }

    // 왼쪽 톱니바퀴가 회전 가능한지 확인
    private static boolean isTurnLeft(int number, int l) {
        if (number < 0) return false;
        return system[number].get(2) != l;
    }

    // 오른쪽 톱니바퀴가 회전 가능한지 확인
    private static boolean isTurnRight(int number, int r) {
        if (number > 3) return false;
        return system[number].get(6) != r;
    }

    // 톱니바퀴 회전
    private static void turn(int direction, int number) {
        if (direction == -1) {
            // 반시계 방향 회전
            system[number].addLast(system[number].removeFirst());
        } else if (direction == 1) {
            // 시계 방향 회전
            system[number].addFirst(system[number].removeLast());
        }
    }

    // 톱니바퀴 시스템 이동
    private static void moveSystem(int number, int direction) {
        if (number < 0 || number > 3) return;

        visited[number] = true;
        int l = system[number].get(6);
        int r = system[number].get(2);

        turn(direction, number); // 현재 톱니바퀴 회전

        // 왼쪽 톱니바퀴가 회전 가능한지 확인하고 재귀 호출
        if (isTurnLeft(number - 1, l) && !visited[number - 1]) {
            moveSystem(number - 1, -direction); // 반대 방향으로 회전
        }

        // 오른쪽 톱니바퀴가 회전 가능한지 확인하고 재귀 호출
        if (isTurnRight(number + 1, r) && !visited[number + 1]) {
            moveSystem(number + 1, -direction); // 반대 방향으로 회전
        }
    }
}
