import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class lecture implements Comparable<lecture> {
        int day, money;

        public lecture(int day, int money) {
            this.day = day;
            this.money = money;
        }

        @Override
        public int compareTo(lecture o) {
            return this.day - o.day;
        }
    }
    private static int N;
    private static List<lecture> lectures = new ArrayList<>();
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int answer;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        answer = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            lectures.add(new lecture(day, money));
        }
        
        Collections.sort(lectures);
        
        for (int i = 0; i < N; i++) {
            pq.add(lectures.get(i).money); // 돈을 넣어준다
            if(pq.size() > lectures.get(i).day) pq.poll();
        }

        while (!pq.isEmpty()) {
            answer += pq.poll();;
        }

        System.out.println(answer);
    }
}
