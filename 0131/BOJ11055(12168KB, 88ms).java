import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 나보다 전에 있는 수가 나보다 작으면 그 수의 이전 꺼(누적) 을 가져오면 된다. 
* 최대값으로 계속 비교해서 가져오기
* 
* */
public class Main {
    private static int N;
    private static int[] A;
    private static int[] dp;
    private static int max;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new int[N];

        max = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            dp[i] = A[i];
            for(int j = 0; j < i; j++) {
                if(A[j] < A[i]) { // 지금 고정된 값이 더 크면
                    dp[i] = Math.max(dp[i], dp[j] + A[i]);
                }

            }
            if(max < dp[i]) {
                max = dp[i];
            }
        }

        System.out.println(max);
    }
}
