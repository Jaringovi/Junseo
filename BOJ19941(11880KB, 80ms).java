import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 규칙 찾아서 냅다 하는건가 ? 라고 생각했는데 그냥 정직하게 생각해서 푸는 문제
* start, end 설정할 때 인덱스 범위 초과 시 어떡하지 ? 하다가 Math.min과 Math.max를 사용해서 
* 넘어봐야 0, N-1로 하게 했다. 
* */
public class Main {
    private static int H,K;
    private static String table;
    private static boolean[] visited;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        table = br.readLine();

        visited = new boolean[table.length()];

        int answer = countMax();
        System.out.println(answer);
    }

    private static int countMax() {
        int answer = 0;
        for(int i = 0; i < table.length(); i++) { // 전체 돌고
            if(table.charAt(i) == 'P') { // 사람 기준
                int start = Math.max(i - K, 0);
                int end = Math.min(i + K, table.length() - 1);
                for(int j = start; j <= end; j++) { // 가장 거리가 먼, 왼쪽에 있는거 먹어야함.
                    if(!visited[j] && table.charAt(j) == 'H') {
                        answer++;
                        visited[j] = true;
                        break;
                    }
                }
            }
        }

        return answer;
    }
}
