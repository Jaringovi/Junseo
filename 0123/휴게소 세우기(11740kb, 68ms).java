import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
* 구 ) 휴게소가 없는 구간의 길이의 최댓값의 최소
* */
public class Main {
    private static int N,M,L;
    private static int[] rest;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        rest = new int[N + 2];
        rest[0] = 0;
        rest[N+1] = L;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            rest[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(rest);

        int answer = binarySearch();
        System.out.println(answer);

    }

    private static int binarySearch() {
        int start = 1;
        int end = L;
        int ans = 0;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(canMatch(mid)) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return ans;
    }

    private static boolean canMatch(int mid) { // 여기에 휴게소 가능?
        int count = 0;
        for(int i = 1; i < rest.length; i++) {
            int gap = rest[i] - rest[i - 1];
            count += (gap - 1) / mid;
        }
        return count <= M;
    }
}
