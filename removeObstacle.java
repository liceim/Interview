/*
Time complexity : O(mn)O(mn).
*/
public class Main {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    public static int minSteps(int numRows, int numColumns, List<List<Integer>> lot) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[numRows][numColumns];
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] point = queue.poll();
                if (lot.get(point[0]).get(point[1]) == 9) {
                    return step;
                }
                for (int j = 0; j < 4; ++j) {
                    int nx = point[0] + dx[j];
                    int ny = point[1] + dy[j];
                    
                    if (nx < 0 || nx >= numRows || ny < 0 || ny >= numColumns || visited[nx][ny]) {
                        continue;
                    }
                    queue.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
            step++;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        List<Integer> lot1 = new ArrayList<>();
        lot1.add(1);
        lot1.add(1);
        lot1.add(0);

        List<Integer> lot2 = new ArrayList<>();
        lot2.add(1);
        lot2.add(0);
        lot2.add(0);
        
        List<Integer> lot3 = new ArrayList<>();
        lot3.add(1);
        lot3.add(9);
        lot3.add(1);
        
        List<List<Integer>> lots = new ArrayList<>();
        lots.add(lot1);
        lots.add(lot2);
        lots.add(lot3);
        
        System.out.println(minSteps(3, 3, lots));
    }
}
