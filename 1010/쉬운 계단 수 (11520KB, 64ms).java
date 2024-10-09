import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int MOD = 1000000000;
    private static long[][] dp;
    private static long result = 0;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        dp = new long[N + 1][10];

        for(int i = 1; i < 10; i++) {
            dp[1][i] = 1; // 길이가 1인 애들의 마지막 자리는 모두 1
        }

        for(int i = 2; i < N + 1; i++) {
            for(int j = 0; j < 10; j++) {
                if(j==0) {
                    dp[i][j] = dp[i-1][1] % MOD;
                } else if(j == 9) {
                    dp[i][j] = dp[i-1][8] % MOD;
                } else {
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % MOD;
                }
            }
        }
        for(int i = 0; i < 10; i++) {
            result = (result + dp[N][i]) % MOD;
        }
        System.out.println(result);
    }
}
