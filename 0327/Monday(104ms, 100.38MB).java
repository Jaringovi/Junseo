class Solution {
    static class Date implements Comparable<Date> {
        int start;
        int end;

        public Date(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Date o) {
            if(this.start != o.start) {
                return this.start - o.start;
            } else {
                return this.end - o.end;
            }
            
        }

    }
    public int countDays(int days, int[][] meetings) {
        PriorityQueue<Date> pq = new PriorityQueue<>();
        int totalVisited = 0;
        for(int i = 0; i < meetings.length; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];
            pq.add(new Date(start, end));
        }

        int prevMaxEnd = 0;
        while(!pq.isEmpty()) {
            Date date = pq.poll();
            if(date.start > prevMaxEnd) { // 지금 시작이 이전의 끝 보다 클 때
                totalVisited += (date.end - date.start + 1);
            }
            
            if(date.start == prevMaxEnd) {
                totalVisited += (date.end - date.start);
            }

            if(date.start < prevMaxEnd && date.end > prevMaxEnd) {
                totalVisited += (date.end - prevMaxEnd);
            }

            if(prevMaxEnd < date.end) {
                prevMaxEnd = date.end;
            }

        }

        return days - totalVisited;

    }
}
