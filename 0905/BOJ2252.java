import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N,M;
    private static List<List<Integer>> tall;
    private static int[] link;
    private static Queue<Integer> queue = new LinkedList<>();
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tall = new ArrayList<>();
        link = new int[N];

        for (int i = 0; i < N; i++) {
            tall.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            tall.get(start).add(end);
            link[end] += 1;
        }

        for(int i = 0; i < N; i++) {
            if(link[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int index = queue.poll();
            sb.append(index + 1).append(" ");
            for(int t : tall.get(index)) {
                link[t] -= 1;
                if (link[t] == 0) queue.offer(t);
            }
        }
        System.out.println(sb);
    }
}
