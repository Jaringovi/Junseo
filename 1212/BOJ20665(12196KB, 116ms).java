package november;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
* 초기 : 가장 가까이에 앉은 사람이 가장 먼 자리를 선호. 만약 독서실 이용하는 사람 없으면 1번
* 초기 조건으로 비교할 수 없다면 (= 가장 먼 자리가 여러개면 ), 좌석 번호가 작은 순으로
* 09 : 00 ~ 21 : 00 운영
* 입실 시간과 퇴실 시간이 찍히고, 정렬된 상태로 들어오는 것은 아니다.
* 구하는 것 : 민규가 선호하는 좌석을 이용할 수 있는 총 '분'
* 조건
* 1. 독서실은 모두 비어있는 상태로 시작
* 2. 독서실 예약 시간이 같다면, 짧은 이용 시간을 가진 사람을 먼저
* 3. 독서실 예약 리스트에 있는 예약 자들이 좌석이 없어서 못 앉는 경우는 존재하지 않는다.
*
* 우선 순위 큐 => 조건 기반으로 예약 뱉게 하기
* 메서드 구현 : 가까이에 앉은 사람이 가장 먼 자리, 같은게 많으면 가장 작은 자리
* visited를 정수형으로 해서 끝나는 시간을 넣을것
* 바보같이 총 사용 시간 분으로 바꾸는거 까먹고 계속 마이너스 나온다고 의문 가지고 있었음
* */
public class BOJ20665 {
    static class Reservation implements Comparable<Reservation> {
        int start, end, use;
        public Reservation(int start, int end, int use) {
            this.start = start;
            this.end = end;
            this.use = use;
        }
        @Override
        public int compareTo(Reservation o) {
            if(this.start == o.start) {
                return this.use - o.use;
            } else {
                return this.start - o.start;
            }
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, T, P; // 좌석의 개수, 예약자 수, 민규 최애 자리
    private static PriorityQueue<Reservation> pq = new PriorityQueue<>();
    private static int[] visited;
    private static int maxTime = 720;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()) - 1;

        visited = new int[N];

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // start와 end를 시, 분으로 나누어 분 단위로 계산
            int startHour = start / 100;    // 시작 시간의 시 부분
            int startMinute = start % 100;  // 시작 시간의 분 부분
            int endHour = end / 100;        // 끝 시간의 시 부분
            int endMinute = end % 100;      // 끝 시간의 분 부분

            // 시작 시간과 끝 시간을 총 분으로 변환
            int startTotalMinutes = startHour * 60 + startMinute;
            int endTotalMinutes = endHour * 60 + endMinute;

            // 사용 시간 계산 (분 단위)
            int use = endTotalMinutes - startTotalMinutes;

            pq.add(new Reservation(start, end, use));
        }
        checkSeat();
        System.out.println(maxTime);

    }

    private static void checkSeat() {
        while (!pq.isEmpty()) {
            Reservation reservation = pq.poll();
            int bestSeat = findBestSeat(reservation.start);
            visited[bestSeat] = reservation.end;
            if(bestSeat == P) {
                maxTime -= reservation.use;
            }
        }
    }

    private static int findBestSeat(int startTime) { // 여러 비어 있는 좌석 중, 가까이 앉은 사람이 가장 먼 거리에 있는 자리 인덱스 출력
        int bestSeat = -1;
        int maxMinDist = -1;
        for (int i = 0; i < N; i++) {
            if(visited[i] <= startTime) { // 빈 좌석
                int minDist = Integer.MAX_VALUE;
                for (int j = 0; j < N; j++) {
                    if (i != j && visited[j] > startTime) { // 점유된 자리와의 거리 계산
                        minDist = Math.min(minDist, Math.abs(i - j));
                    }
                }
                if(maxMinDist < minDist || (minDist == maxMinDist && i < bestSeat)) {
                    maxMinDist = minDist;
                    bestSeat = i;
                }
            }
        }

        // 모든 좌석이 비어있을 때, 기본적으로 가장 낮은 번호의 좌석 반환
        if (bestSeat == -1) {
            for (int i = 0; i < N; i++) {
                if (visited[i] <= startTime) {
                    bestSeat = i;
                    break;
                }
            }
        }
        return bestSeat;
    }
}
