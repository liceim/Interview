/*
It was basic DFS approch used to solve the question, where we are loopingover all the indices and if the value
of any cell(i,j) is 1, then we are finding allthe 1's we can reach from that cell, once we cannot find any more 1's
connected to it, we will move on to find another 1 . I was avoiding extra memory which might have been used
to track already visited 1's by changing its value to 0, once its visited
Whatis the run time complexity of your solution for this code question.
Its traversing each cell atmost once in worst case, so the time complexity is O(m*n) where m*n is total cells in
matrix

*/
public int numIslands(char[][] grid) {
    int count=0;
    for(int i=0;i<grid.length;i++)
        for(int j=0;j<grid[0].length;j++){
            if(grid[i][j]=='1'){
                dfsFill(grid,i,j);
                count++;
            }
        }
    return count;
}
private void dfsFill(char[][] grid,int i, int j){
    if(i>=0 && j>=0 && i<grid.length && j<grid[0].length&&grid[i][j]=='1'){
        grid[i][j]='0';
        dfsFill(grid, i + 1, j);
        dfsFill(grid, i - 1, j);
        dfsFill(grid, i, j + 1);
        dfsFill(grid, i, j - 1);
    }
}
