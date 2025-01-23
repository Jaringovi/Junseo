import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 최대 문제를 구해보자
* */
public class Main {
    private static int N,T;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] dp;
    private static int[][] arr;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[N+1][2];
        dp = new int[N + 1][T + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= T; j++) {
                if(j - arr[i][0] >= 0) {
                    dp[i][j] = Math.max(dp[i-1][j], arr[i][1] + dp[i-1][j-arr[i][0]]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N][T]);
    }
}
