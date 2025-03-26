class Solution {
    /*
    배열을 오름차순으로 정렬했을 때: 평균(mean)은 거리의 제곱합을 최소화하고, 중앙값(median)은 거리의 절댓값 합을 최소화
     */
    public int minOperations(int[][] grid, int x) {
        List<Integer> number = makeGrid(grid);
        int remain = number.get(0) % x;
        for(int num : number) {
            if(num % x != remain) return -1;
        }
        
        Collections.sort(number);
        int mid = number.get(number.size() / 2);
        int answer = 0;
        for(int num : number) {
            answer += Math.abs(num - mid) / x;
        }

        return answer;

    }

    public List<Integer> makeGrid(int[][] grid) {
        List<Integer> number = new ArrayList<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                number.add(grid[i][j]);
            }
        }

        return number;
    }
}
