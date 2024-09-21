package september.twenty_one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3079 {
    private static int N,M;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static long[] time;
    private static long result = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        time = new long[(int) N];

        for (int i = 0; i < N; i++) {
            time[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(time);
        binarySearch();
        System.out.println(result);
    }

    private static void binarySearch() {
        long min = 0;
        long max = time[N-1] * M;
        while(min <= max) {
            long mid = (max + min) / 2;
            long sum = 0;
            for(long num : time){
                long count = mid / num;
                if(sum >= M) break;
                sum += count;
            }
            if(sum >= M){
                max = mid - 1;
                result = Math.min(result, mid);
            } else {
                min = mid + 1;
            }
        }
    }
}
