/*
Here I take a queue with initialied with row=0,col=0 and I am keeping the track of the counts that is steps
taken which is initialy 0. And also i keep the set of visited coordinats to keep the track which indexes i have
visited. And the while the queue is there, i take the row, col, count out and loop for all the four directions and
check if it is in the lot boundary then i check for obstacle and if not visited i add in visited set and append in
queue and return the count for the steps taken to remove it starting from 1st step. And i check if it is not the
trench and the index (row,col) is not visited i visit it and add in the queue for further processing. Else I return
-1 ifit is no possible to get the count
Time complexity : O(m*n) where m is number of rows and n is number of columns.
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
