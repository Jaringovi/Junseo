import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Pair implements Comparable<Pair> {
        int num, count;
        public Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.count != o.count) {
                return Integer.compare(this.count, o.count); // count가 작은 게 앞으로
            }
            return Integer.compare(this.num, o.num); // number가 작은 게 앞으로
        }
    }

    private static int r,c,k;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] A = new int[101][101];
    private static int lenRow = 3;
    private static int lenCol = 3;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int now = makeAnswer();
        System.out.println(now);



    }

    private static int makeAnswer() {
        for(int i = 0; i <= 100; i++) {
            if(A[r][c] == k) {
                return i;
            }
            solve();
        }
        return -1;
    }

    private static void solve() {
        if(lenRow >= lenCol) {
            for(int i = 1; i <= lenRow; i++) {
                R(i);
            }
        } else {
            for(int i = 1; i <= lenCol; i++) {
                C(i);
            }
        }
    }

    private static void C(int num) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i <= lenRow; i++) {
            if(A[i][num] == 0) continue;
            map.put(A[i][num], map.getOrDefault(A[i][num], 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new Pair(entry.getKey(), entry.getValue()));
        }

        int i = 1;
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            A[i++][num] = p.num;
            A[i++][num] = p.count;
        }

        lenRow = Math.max(lenRow, i);

        while(i <= 99) {
            A[i++][num] = 0;
            A[i++][num] = 0;
        }
    }

    private static void R(int num) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i <= lenCol; i++) {
            if(A[num][i] == 0) continue;
            map.put(A[num][i], map.getOrDefault(A[num][i], 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new Pair(entry.getKey(), entry.getValue()));
        }

        int i = 1;
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            A[num][i++] = p.num;
            A[num][i++] = p.count;
        }

        lenCol = Math.max(lenCol, i);

        while(i <= 99) {
            A[num][i++] = 0;
            A[num][i++] = 0;
        }
    }
}
