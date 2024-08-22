package algo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class BOJ14888 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] operator;
    private static int[] numbers;
    private static int max;
    private static int min;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
    	
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            operator = new int[4];
            numbers = new int[N];
            
            
            for(int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken()); 
            }
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++) {
                operator[i] = Integer.parseInt(st.nextToken()); // + - * / 2 1 0 1 이런식으로 숫자로 들어옴
            }
             
             
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
             
            calculate(0, numbers[0]);
            sb.append(max).append("\n").append(min);
    
        System.out.println(sb);
    }
     
    private static void calculate(int depth, int result) {
        if(depth == N-1) {
            if(result < min) {
                min = result;
            }
            if(result > max) {
                max = result;
            }
            return;
        } else {
            for(int i = 0; i < 4; i++) {
                if(operator[i] > 0) {
                    operator[i] -= 1;
                     
                    int tempResult = result;
                     
                    switch(i) {
                        case 0:
                            result += numbers[depth + 1];
                            break;
                        case 1:
                            result -= numbers[depth + 1];
                            break;
                        case 2:
                            result *= numbers[depth + 1];
                            break;
                        case 3:
                            result /= numbers[depth + 1];
                            break;
                    }
                    calculate(depth + 1, result);
                     
                    result = tempResult;
                    operator[i] += 1;
                }
            }
        }
    }
}
