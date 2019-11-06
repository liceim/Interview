//Time complexity: O(r * c).
//Space cmplexity: O(r * c).
/*
Its a variant of Breadth first search, where I am storing all the cell position where value is 1 and adding it to a
queue. Then dequeing items from this queue and changing all its valid neighbours(having value 0) to 1 and
enqueuing back to queue. so this process will continue tillthere are no elements in queue(means no more 0's
are present in the matrix) To track the number of hours it is taking to convert all the 0's to 1's, after
processing every level for queue, Iam increasing the hours by 1.
Whatis the run time complexity of your solution for this code question.
In all case it willtake O(m*n) time to convert allthe 0s to 1, as it has to check every cell item at least once.
*/

class Solution {
  public static void main(String[] args) {
      int[][] grid = { { 0, 0, 1, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0 } };
      List<List<Integer>> gg = new ArrayList<>();
      for (int i = 0; i < grid.length; ++i) {
          List<Integer> list = new ArrayList<>();
          for (int j = 0; j < grid[0].length; ++j) {
              list.add(grid[i][j]);
          }
          gg.add(list);
      }
      System.out.println(minHours(gg));
  }

  private static final int FILL = 1;
  private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public static int minHours((int rows, int columns, List<List<Integer>> grid) {
      if (grid == 0 || grid.length == 0 || grid[0].length == 0 || rows != grid.length || columns != grid[0].length) {
          return 0;          
      }
          
      int count = 0;
      Queue<Point> queue = new ArrayDeque<>();
      for (int r = 0; r < rows; r++) {
          for (int c = 0; c < columns; c++) {
              if (grid.get(r).get(c) == FILL) {
                  queue.offer(new Point(r, c));
                  count++;
              }
          }
      }
    
      int steps = 0;
      while (!queue.isEmpty()) {
          int size = queue.size();
          if (count == rows * columns) {
              return steps;
          }
          for (int i = 0; i < size; ++i) {
              Point server = queue.poll();

              for (int[] dir : DIRS) {
                  int r = server.r + dir[0];
                  int c = server.c + dir[1];

                  if (isHuman(grid, r, c)) {
                      count++;
                      grid.get(r).set(c, FILL);
                      queue.offer(new Point(r, c));
                  }
              }
          }
          steps++;
      }
      return -1;
  }

  private static boolean isFill(List<List<Integer>> grid, int r, int c) {
      return r >= 0 && r < grid.size() &&c >= 0 && c < grid.get(0).size() && grid.get(r).get(c) != FILL;
  }

  private static class Point {
      int r, c;
      Point(int r, int c) {
          this.r = r;
          this.c = c;
      }
  }
}
