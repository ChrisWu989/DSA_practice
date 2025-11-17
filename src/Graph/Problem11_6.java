package Graph;

import java.util.*;

public class Problem11_6 {
    /*
    Problem 55
    There are N students in a class. Some of them are friends, while some are not.
    Their friendship is transitive in nature. For example, if A is a direct friend of B, 
    and B is a direct friend of C, then A is an indirect friend of C. 
    We define a friend circle as a group of students who are direct or indirect friends.
    Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, 
    then the ith and jth students are direct friends with each other, otherwise not.
    And you have to output the total number of friend circles among all the students.

    Example 1:
    Input: 
    [[1,1,0],
    [1,1,0],
    [0,0,1]]
    Output: 2
    Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
    The 2nd student himself is in a friend circle. So return 2.

    Example 2:
    Input: 
    [[1,1,0],
    [1,1,1],
    [0,1,1]]
    Output: 1
    Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
    so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1
    */
    public static int friendCircle(int[][] M){
        int v = M.length;
        boolean[] visited = new boolean[v];
        int count = 0;

        for(int i = 0; i < v; i++){
            if(!visited[i]){
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int[][] M, boolean[] visited, int i) {
        visited[i] = true;
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                dfs(M, visited, j);
            }
        }
    }

    /*
    Leetcode 994: Rotting Oranges
    Problem 56
    Given a matrix of dimension M * N where each cell in the matrix can have values 0, 1 or 2 which has the following meaning:  

    0: Empty cell
    1: Cells have fresh oranges
    2: Cells have rotten oranges
    Determine what is the minimum time required so that all the oranges become rotten. 
    A rotten orange at index (i,j ) can rot other fresh oranges which are its neighbours (up, down, left and right). 
    If it is impossible to rot every orange then simply return -1.

    Examples: 
    Input:  arr[][] = { {2, 1, 0, 2, 1}, {1, 0, 1, 2, 1}, {1, 0, 0, 2, 1}};
    Output: 2
    */
    public static int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        int time = 0;

        // Step 1: Collect all rotten oranges and count fresh ones
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j, 0});  // {row, col, time}
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // Directions: up, down, left, right
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0]; // row
            int c = current[1]; // col
            int t = current[2]; // time
            time = Math.max(time, t);
            
            for (int[] dir : directions) {
                int newr = r + dir[0];
                int newc = c + dir[1];

                // Infecting adjacent oranges by going through left right top down
                if (newr >= 0 && newr < rows && newc >= 0 && newc < cols && grid[newr][newc] == 1) {
                    grid[newr][newc] = 2;  // rot it
                    freshCount--;
                    queue.offer(new int[]{newr, newc, t + 1});
                }
            }
        }

        // Step 3: If fresh oranges remain, return -1
        return (freshCount == 0) ? time : -1;
    }
    /*
    Leetcode 200: Number of Islands
    Problem 57
    Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
    You may assume all four edges of the grid are all surrounded by water.

    Example 1:
    Input:
    11110
    11010
    11000
    00000
    Output: 1
     */
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == '1'){
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int r, int c){
        if(r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0'){
            return;
        }

        // Set this land to visited
        grid[r][c] = '0';

        //Connected lands are right, left, up, down
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }


    /*
    Leetcode 212: Word Search II
    Problem 58
    Given a 2D board and a list of words from the dictionary, find all words in the board.
    Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighbouring. The same letter cell may not be used more than once in a word.
    Example:
    Input:
    board = [
    ['o','a','a','n'],
    ['e','t','a','e'],
    ['i','h','k','r'],
    ['i','f','l','v']
    ]
    words = ["oath","pea","eat","rain"]
    
    Output: ["eat","oath"]
    Note:
    All inputs are consist of lowercase letters a-z.
    The values of words are distinct.
    */
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) {
                result.add(word);
            }
        }
        return result;
    }

    private static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, String word, int r, int c, int index, boolean[][] visited) {
        // Base cases
        if (index == word.length()) return true;
        
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length
                || visited[r][c] || board[r][c] != word.charAt(index)) {
            return false;
        }

        // Mark as visited
        visited[r][c] = true;

        // Explore all 4 directions
        boolean found = dfs(board, word, r + 1, c, index + 1, visited)
                || dfs(board, word, r - 1, c, index + 1, visited)
                || dfs(board, word, r, c + 1, index + 1, visited)
                || dfs(board, word, r, c - 1, index + 1, visited);

        // Backtrack
        visited[r][c] = false;

        return found;
    }
    /*
    Leetcode 361: Bomb Enemy
    Problem 59
    Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb. 
    The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
    Note: You can only put the bomb at an empty cell.

    Example 1:
    Input:
    {{'0','E','0','0'},
    {'E','0','W','E'},
    {'0','E','0','0'}}
    Output: 3
    (Placing a bomb at (1,1) kills 3 enemies)

    Example:2
    Input:
    grid =["0 E 0 0",
            "E E W E",
            "0 E 0 0"]
    Output: 2
    Placing a bomb at (0,0) or (0,3) or (2,0) or (2,3) kills 2 enemies
    */
    public static int maxBombed(char[][] grid){
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int maxE = 0;

        int rowHits = 0;             // Enemies count in the current row segment
        int[] colHits = new int[cols];  // Enemies count in each column segment

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                // Case for the row
                if (j == 0 || grid[i][j - 1] == 'W'){ // Wall or start of row
                    rowHits = 0;
                    for (int k = j; k < cols && grid[i][k] != 'W'; k++) {
                        if (grid[i][k] == 'E') rowHits++;
                    }
                }

                // Case for the col
                if (i == 0 || grid[i - 1][j] == 'W') {
                    colHits[j] = 0;
                    for (int k = i; k < rows && grid[k][j] != 'W'; k++) {
                        if (grid[k][j] == 'E') colHits[j]++;
                    }
                }

                // Empty Cell compute total kills from col and row
                if (grid[i][j] == '0') {
                    int total = rowHits + colHits[j];
                    maxE = Math.max(maxE, total);
                }
            }
        }
        return maxE;
    }


    /*
    Problem 28
    You are given a matrix consisting of N rows and M columns. Each cell of the matrix contains one digit (0-9).
    Cells are adjacent if they share a common edge. It is possible to move from one cell to another directly only if they are adjacent
    Find the largest group of cells such that
        - You can get from any cell in the group to any other by moving only through cells that belong to the group
        - The difference between the alrgest and the smallest values of the cells in the group is at most 1
    Write a function that given a matrix A of N rows and M columns containing integers from the range 0-9,
    returns the maximal size of the group, fufillinf the above criteria
    
    Exmaple 1
    A = {{3, 4, 6}, {2, 7, 6}} returns 3

    Example 2
    A = {{3, 3, 5, 6}, {6, 7, 2, 2}, {5, 2, 3, 8}, {5, 9, 2, 3}, {1, 2, 3, 4}} returns 8

    Example 3
    A = {{4, 4, 2, 4, 4, 4}} returns 3

    Example 4
    A = {{0} , {3}, {5}} returns 1
    */
    static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static int largestGroup(int[][] A) {
        if (A == null || A.length == 0) return 0;

        int rows = A.length;
        int cols = A[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int maxGroup = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    int size = dfs(A, i, j, A[i][j], A[i][j], visited);
                    maxGroup = Math.max(maxGroup, size);
                }
            }
        }

        return maxGroup;
    }

    private static int dfs(int[][] A, int r, int c, int minVal, int maxVal, boolean[][] visited) {
        int rows = A.length;
        int cols = A[0].length;

        // Boundary Checks
        if (r < 0 || c < 0 || r >= rows || c >= cols || visited[r][c]) return 0;

        int val = A[r][c];

        // Current val differs too much from min/max
        if (Math.abs(val - minVal) > 1 || Math.abs(val - maxVal) > 1) return 0;

        visited[r][c] = true;
        int newMin = Math.min(minVal, val);
        int newMax = Math.max(maxVal, val);

        int count = 1;
        for (int[] d : directions) {
            count += dfs(A, r + d[0], c + d[1], newMin, newMax, visited);
        }

        return count;
    }
     
    public static void main(String[] args) {
        // Problem 55
        // int[][] friends = {
        //     {0, 0, 0}how,
        //     {0, 0, 0},
        //     {0, 1, 1}
        // };
        // int result = friendCircle(friends);
        // System.out.println(result);

        // Problem 56
        // int[][] oranges = {
        //     {2, 1, 0, 2, 1},
        //     {1, 0, 1, 2, 1},
        //     {1, 0, 0, 2, 1}
        // };
        // int result = orangesRotting(oranges);
        // System.out.println(result);

        // Problem 57
        // char[][] islands = {
        //     {'1', '1', '1', '1', '0'},
        //     {'1', '1', '0', '1', '0'},
        //     {'1', '1', '0', '0', '0'},
        //     {'0', '0', '0', '0', '1'}
        // };
        // int result = numIslands(islands);
        // System.out.println(result);

        // Problem 58
        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };

        String[] words = {"oath","pea","eat","rain"};
        List<String> res = findWords(board, words);
        for (String s : res){
            System.out.println(s);
        }

        // Problem 59
        // char[][] grid1 = {
        //     {'0','E','0','0'},
        //     {'E','0','W','E'},
        //     {'0','E','0','0'}
        // };

        // char[][] grid2 = {
        //     {'0','E','0','0'},
        //     {'E','E','W','E'},
        //     {'0','E','0','0'}
        // };

        // System.out.println(maxBombed(grid1)); // Expected 3
        // System.out.println(maxBombed(grid2)); // Expected 2

        // Problem 28
        // int[][] A1 = {{3, 4, 6}, {2, 7, 6}};
        // System.out.println(largestGroup(A1)); // 3

        // int[][] A2 = {
        //     {3, 3, 5, 6},
        //     {6, 7, 2, 2},
        //     {5, 2, 3, 8},
        //     {5, 9, 2, 3},
        //     {1, 2, 3, 4}
        // };
        // System.out.println(largestGroup(A2)); // 8

        // int[][] A3 = {{4, 4, 2, 4, 4, 4}};
        // System.out.println(largestGroup(A3)); // 3

        // int[][] A4 = {{0}, {3}, {5}};
        // System.out.println(largestGroup(A4)); // 1
    }
}
