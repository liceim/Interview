//Time complexity: O(r * c).
//Space cmplexity: O(r * c).
//BFS

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

  private static final int ZOMBIE = 1;
  private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public static int minHours((int rows, int columns, List<List<Integer>> grid) {
      if (grid == 0 || grid.length == 0 || grid[0].length == 0) {
          return 0;          
      }
      
      int count = 0;
      Queue<Point> queue = new ArrayDeque<>();
      for (int r = 0; r < rows; r++) {
          for (int c = 0; c < columns; c++) {
              if (grid.get(r).get(c) == ZOMBIE) {
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
              Point zombie = queue.poll();

              for (int[] dir : DIRS) {
                  int r = zombie.r + dir[0];
                  int c = zombie.c + dir[1];

                  if (isHuman(grid, r, c)) {
                      count++;
                      grid.get(r).set(c, ZOMBIE);
                      queue.offer(new Point(r, c));
                  }
              }
          }
          steps++;
      }
      return -1;
  }

  private static boolean isHuman(List<List<Integer>> grid, int r, int c) {
      return r >= 0 && r < grid.size() &&c >= 0 && c < grid.get(0).size() && grid.get(r).get(c) != ZOMBIE;
  }

  private static class Point {
      int r, c;
      Point(int r, int c) {
          this.r = r;
          this.c = c;
      }
  }
}
