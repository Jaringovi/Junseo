package september.twenty_one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1541 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split("-");

        int answer = Integer.MAX_VALUE;

        for(int i = 0; i < input.length; i++) {
            int temp = 0;

            String[] addition = input[i].split("\\+");

            for(int j = 0; j < addition.length; j++) {
                temp += Integer.parseInt(addition[j]);
            }

            if(answer == Integer.MAX_VALUE) {
                answer = temp;
            } else {
                answer -= temp;
            }
        }

        System.out.println(answer);
    }
}
