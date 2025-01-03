import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge> {
        int start, end;
        long weight;

        public Edge(int start, int end, long weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    private static void makeSet() {
        for(int i = 0; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int v) {
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        if(rootA < rootB) parents[rootB] = rootA;
        else parents[rootA] = rootB;

        return true;
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Edge[] edges;
    private static int N,M;
    private static int[] parents;
    private static long total;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new Edge[M];
        parents = new int[N+1];
        total = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long weight = Integer.parseInt(st.nextToken());
            total += weight;
            edges[i] = new Edge(start, end, weight);
        }

        Arrays.sort(edges);
        makeSet();

        long count = 0;
        long cost = 0;
        for(Edge e : edges) {
            if(union(e.start, e.end)) {
                cost += e.weight;
                if(++count == N-1) break;
            }
        }
        if(count == N-1) System.out.println(total - cost);
        else System.out.println(-1);
    }
}
