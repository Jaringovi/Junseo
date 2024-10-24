import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N,M,K;
	private static String[] lamps;
	private static int answer = 0;
	private static Map<String, Integer> record = new HashMap<>();
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lamps = new String[N];
		
		
		for(int i = 0; i < N; i++) {
			lamps[i] = br.readLine();
		}
		
		K = Integer.parseInt(br.readLine());
		
		turnOn();
		
		System.out.println(answer);
		
	}
	
	
	private static void turnOn() {
		for(int i = 0; i < N; i++) {
			String temp = lamps[i];
			int zero = 0;
			for(int j = 0; j < M; j++) {
				if(lamps[i].charAt(j) == '0') {
					zero++;
				}
			}
			
			if(zero <= K && (K - zero) % 2 == 0) {
				record.put(temp, record.getOrDefault(temp, 0) + 1);
			}
		}
		
		
		for(Map.Entry<String, Integer> temp : record.entrySet()) {
			answer = Math.max(answer, temp.getValue());
		}
	}
}
