import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N,M;
	private static long[] money;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		money = new long[N];
		
		long start = Integer.MIN_VALUE;
		long end = 100000 * 10000;
		
		for(int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(br.readLine());
			start = Math.max(start, money[i]);
		}
		long answer = binarySearch(start, end);
		System.out.println(answer);
	}
	
	
	private static long binarySearch(long start, long end) {
		long result = end;
		while(start <= end) {
			long mid = (start + end) / 2;
			long count = 1;
			long now = mid;
			for(int i = 0; i < N; i++) {
				if(money[i] <= now) {
					now -= money[i];
				} else {
					count += 1; // 인출
					now = mid; // 인출
					now -= money[i];
				}
			}
			
			if(count > M) {
				start = mid + 1;
			} else {
				result = mid;
				end = mid - 1;
			}
		}
		return result;
	}

}
