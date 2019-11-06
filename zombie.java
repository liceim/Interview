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

  public static int minHours(List<List<Integer>> grid) {
      int people = 0;
      int ans = 0;
      Queue<Point> queue = new ArrayDeque<>();
      for (int r = 0; r < grid.size(); r++) {
          for (int c = 0; c < grid.get(0).size(); c++) {
              if (grid.get(r).get(c) == ZOMBIE) {
                  queue.offer(new Point(r, c));
              } else {
                  people++;
              }
          }
      }

      if (people == 0) return ans;
      
      ans++;
      while (!queue.isEmpty()) {
          int size = queue.size();
          for (int i = 0; i < size; ++i) {
              Point zombie = queue.poll();

              for (int[] dir : DIRS) {
                  int r = zombie.r + dir[0];
                  int c = zombie.c + dir[1];

                  if (isHuman(grid, r, c)) {
                      people--;
                      if (people == 0) return ans;
                      grid.get(r).set(c, ZOMBIE);
                      queue.offer(new Point(r, c));
                  }
              }
          }
          ans++;
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
