package october.ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1062 {
    private static int N,K; // N : 남극에 있는 단어 개수 K : 읽을 수 있는 단어의 개수가 최대가 되는 수
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static char[] alreadyKnow = "antic".toCharArray();
    private static char[] candidates = "bdefghjklmopqrsuvwxyz".toCharArray();
    private static String[] words;
    private static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken();
        }
        K -= 5;
        if(K < 0) {
            System.out.println(0);
            return;
        }
        makeComb(0, new char[K], 0, new boolean[21]);

        System.out.println(answer);
    }

    static void makeComb(int depth, char[] selected, int start, boolean[] visited) {
        if(depth == K) {
            int temp = calculate(selected);
            answer = Math.max(answer, temp);
            return;
        }
        for(int i = start; i < 21; i++) {
            if(!visited[i]) {
                visited[i] = true;
                selected[depth] = candidates[i];
                makeComb(depth + 1, selected, i + 1, visited);
                visited[i] = false;
            }
        }
    }

    private static int calculate(char[] selected) {
        int temp = 0;
        for(String s : words) {
            if(canRead(s, selected)) {
                temp ++;
            }
        }
        return temp;
    }
    private static boolean canRead(String word, char[] selected) {
        boolean[] alpha = new boolean[26];

        // 이미 알고 있는 "antic"을 true로 설정
        for (char c : alreadyKnow) {
            alpha[c - 'a'] = true;
        }

        for (char c : selected) {
            alpha[c - 'a'] = true;
        }

        boolean isPossible = true;
        for (char c : word.toCharArray()) {
            if(!alpha[c - 'a']) {
                isPossible = false;
            }
        }
        return isPossible;
    }
}
