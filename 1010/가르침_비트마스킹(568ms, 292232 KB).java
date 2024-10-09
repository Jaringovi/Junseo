import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1062_bitmask {
    private static int N, K;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final String alreadyKnow = "antic";  // 이미 알고 있는 알파벳
    private static final String candidates = "bdefghjklmopqrsuvwxyz";  // 선택 가능한 나머지 알파벳
    private static String[] words;
    private static int answer = 0;  // 최댓값을 저장할 변수
    private static int alreadyKnowBitmask = 0;  // 이미 알고 있는 알파벳을 비트로 처리한 값

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        if (K < 5) { // "antic"을 배우려면 최소 5개의 알파벳이 필요
            System.out.println(0);
            return;
        }

        K -= 5;  // 이미 알고 있는 "antic" 5글자를 제외한 나머지 선택할 수 있는 알파벳 수

        // 이미 알고 있는 "antic"을 비트마스크로 처리
        for (char c : alreadyKnow.toCharArray()) {
            alreadyKnowBitmask |= (1 << (c - 'a'));
        }

        makeComb(0, 0, 0);

        System.out.println(answer);
    }

    // depth: 현재까지 선택한 알파벳 수, bitmask: 현재 선택된 알파벳들을 나타내는 비트마스크
    static void makeComb(int depth, int bitmask, int start) {
        if (depth == K) {
            int temp = calculate(bitmask);
            answer = Math.max(answer, temp);
            return;
        }
        for (int i = start; i < candidates.length(); i++) {
            int nextBitmask = bitmask | (1 << (candidates.charAt(i) - 'a'));
            makeComb(depth + 1, nextBitmask, i + 1);
        }
    }

    private static int calculate(int bitmask) {
        int count = 0;
        int fullMask = bitmask | alreadyKnowBitmask;  // 선택된 알파벳 + 이미 알고 있는 알파벳

        for (String word : words) {
            boolean canRead = true;
            for (char c : word.toCharArray()) {
                if ((fullMask & (1 << (c - 'a'))) == 0) {  // 읽을 수 없는 알파벳이 있다면
                    canRead = false;
                    break;
                }
            }
            if (canRead) count++;
        }
        return count;
    }
}
