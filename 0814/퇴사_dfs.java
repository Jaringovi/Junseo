package august.fifteen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class free_dfs {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static List<int[]> days = new ArrayList<>();
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int T = Integer.parseInt(input[0]);
            int W = Integer.parseInt(input[1]);
            days.add(new int[]{T, W});
        }
        dfs(0,0);
        System.out.println(answer);
    }

    private static void dfs(int day, int money){
        if(day >= N){
            answer = Math.max(answer, money);
            return;
        } else if(day + days.get(day)[0] <= N){
            dfs(day + days.get(day)[0], money + days.get(day)[1]);
        }
      dfs(day + 1, money);
    }
}
