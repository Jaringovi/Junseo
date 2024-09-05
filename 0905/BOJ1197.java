import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int V,E;
    static int[] parents;
    static class Edge implements Comparable<Edge> {
        int start, end, weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    private static void makeSet(){
        parents = new int[V];
        for (int i = 0; i < V; i++) {
            parents[i] = -1;
        }
    }

    private static int findSet(int v){
        if(parents[v] < 0) return v;
        return parents[v] = findSet(parents[v]);
    }

    private static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);
        if(rootA == rootB) return false;
        if(rootA < rootB) parents[rootB] = rootA;
        else parents[rootA] = rootB;
        return true;
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(edges);
        makeSet();

        int cnt = 0;
        int cost = 0;
        for(Edge edge : edges){
            if(union(edge.start, edge.end)){
                cost += edge.weight;
                if(++cnt == V-1) break;
            }
        }
        System.out.println(cost);
    }
}
