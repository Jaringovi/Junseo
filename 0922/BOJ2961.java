package september.twenty_one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2961 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] bitters;
    private static int[] sours;
    private static int answer;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        bitters = new int[N];
        sours = new int[N];
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sours[i] = s;
            bitters[i] = b;

        }
        dfs(0,1,0, 0);
        System.out.println(answer);
    }

    private static void dfs(int index, int sour, int bitter, int select) {
        if(index == N) {
            if(select != 0){
                answer = Math.min(answer, Math.abs(sour - bitter));
            }
            return;
        }
        dfs(index + 1, sour * sours[index], bitter + bitters[index], select + 1);
        dfs(index + 1, sour, bitter, select);
    }
}
