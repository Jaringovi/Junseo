package september.eleven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2110 {
    private static int N,C;
    private static int[] houses;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Stack<Integer> routers;
    private static int start, end;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        solve();
        System.out.println(end);

    }

    private static void solve() {
        start = 1;
        end = houses[N-1] - houses[0];
        while(start <= end) {
            int mid = (start + end) / 2;
            routers = new Stack<>();
            routers.push(houses[0]);
            for(int house : houses){
                if(house - routers.peek() >= mid){
                    routers.push(house);
                }
            }
            if(routers.size() >= C){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
    }
}
