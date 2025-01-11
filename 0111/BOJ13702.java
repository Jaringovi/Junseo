package snake.jan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13702 {
    private static int N,K;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] alcohol;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        alcohol = new int[N];

        for(int i = 0; i < N; i++) {
            alcohol[i] = Integer.parseInt(br.readLine());
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            if(alcohol[i] > max) {
                max = alcohol[i];
            }
        }

        long result = binarySearch(max);
        System.out.println(result);
    }

    private static long binarySearch(long num) {
        long start = 1;
        long end = num;
        long ans = 0;
        while(start <= end) {
            int count = 0;
            long mid = (start + end) / 2;

            for(int i = 0; i < N; i++) {
                long temp = alcohol[i] / mid;
                count += temp; // 지금까지 갯수 누적 시키기
            }

            if(count >= K) {
                start = mid + 1; // 최대니까
                ans = mid;
            } else {
                end = mid - 1;
            }
        }

        return ans;
    }
}
