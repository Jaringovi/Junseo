import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, R;
    private static int[] recommends;
    private static HashMap<Integer, int[]> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());
        recommends = new int[R];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            recommends[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < R; i++) {
            int student = recommends[i];
            int[] temp = map.get(student);

            if(temp != null) { // 이미 있는 경우
                temp[1]++;
            } else { // 지금 추천 받은 곳에 없는 경우
                if(map.size() == N) {
                    // 사진틀이 모두 차있으면 가장 추천 횟수가 적은 학생을 찾아 삭제
                    int minKey = -1;
                    int[] minValue = null;

                    for(Map.Entry<Integer, int[]> entry : map.entrySet()) {
                        int[] current = entry.getValue();
                        // 추천 횟수 적거나, 추천 횟수가 같으면 오래된 학생을 삭제
                        if(minValue == null || current[1] < minValue[1] ||
                                (current[1] == minValue[1] && current[0] < minValue[0])) {
                            minKey = entry.getKey();
                            minValue = current;
                        }
                    }

                    // 가장 추천 횟수가 적거나 오래된 학생 삭제
                    if(minKey != -1) {
                        map.remove(minKey);
                    }
                }

                // 새로운 학생 추가
                map.put(student, new int[]{i, 1});  // 새로운 학생은 추천 횟수 1
            }
        }

        // 결과 출력
        int[] result = map.keySet().stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(result);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
