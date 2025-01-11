package snake.jan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* 입력을 쭉 보면서 접두어가 포함되어 있으면 끝나는 문제. 따라서 트라이 문제라고 볼 수 있다.
* 트리에 쭉 넣고, 하나씩 돌면서 이것이 포함되어 있는지 보면 풀리는 문제.
* */
public class BOJ5052 {
    static class Node {
        HashMap<Character, Node> children = new HashMap<>();
        boolean terminal;

        Node() {}

        public void insert(String word) {
            Node node = this; // 현재 메서드를 호출한 Node의 객체
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                node.children.putIfAbsent(c, new Node());
                node = node.children.get(c); // 아래 노드로 이동

                if(i == word.length() - 1) {
                    node.terminal = true;
                    return;
                }

            }
        }


        public boolean contains(String word) { // 포함 하는지 확인
            Node node = this;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Node child = node.children.get(c);

                if(child == null) {
                    return false;
                }

                node = child;
            }

            if(node.terminal) {
                if(node.children.isEmpty()) { // 자식이 비어있으면
                    return false;
                }
            }

            return true;
        }


    }

    private static int T,N;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<String> numbers;
    private static boolean isContent;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            numbers = new ArrayList<>();
            Node root = new Node();
            isContent = true;
            N = Integer.parseInt(br.readLine());
            for(int n = 0; n < N; n++) {
                String str = br.readLine();
                root.insert(str);
                numbers.add(str);
            }

            for(String word : numbers) {
                if(root.contains(word)) {
                    isContent = false;
                    break;
                }
            }
            System.out.println(isContent ? "YES" : "NO");
        }
    }
}
