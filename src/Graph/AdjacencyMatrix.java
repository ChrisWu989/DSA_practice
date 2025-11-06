package Graph;

import java.util.*;

public class AdjacencyMatrix {
    public static int[][] buildAdjacencyMatrix(String[] vertices, String[][] edges) {
        int V = vertices.length;
        int[][] matrix = new int[V][V];

        // Create map from vertex name → index
        Map<String, Integer> vertexIndex = new HashMap<>();
        for (int i = 0; i < V; i++) {
            vertexIndex.put(vertices[i], i);
        }

        // Fill matrix based on edges
        for (String[] edge : edges) {
            String from = edge[0]; //starting
            String to = edge[1]; //destination

            int u = vertexIndex.get(from); //gets index from map using vertex name
            int v = vertexIndex.get(to);

            matrix[u][v] = 1; // directed graph
        }

        return matrix;
    }

    public static void printMatrix(String[] vertices, int[][] matrix) {
        System.out.print("     ");
        for (String vertex : vertices) {
            System.out.print(vertex + " ");
        }
        System.out.println();

        for (int i = 0; i < vertices.length; i++) {
            System.out.print(vertices[i] + "  ");
            for (int j = 0; j < vertices.length; j++) {
                System.out.print("  " + matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void bfs(String[] vertices, int[][] matrix, String start) {
        boolean[] visited = new boolean[vertices.length];
        Queue<Integer> queue = new LinkedList<>();

        int startIndex = Arrays.asList(vertices).indexOf(start);
        //No starting vertex
        if (startIndex == -1) {
            return;
        }

        visited[startIndex] = true;
        queue.add(startIndex);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(vertices[current] + " ");

            // explore all adjacent vertices
            for (int i = 0; i < vertices.length; i++) {
                if (matrix[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    public static void dfs(String[] vertices, int[][] matrix, String start) {
        boolean[] visited = new boolean[vertices.length];
        Stack<Integer> stack = new Stack<>();

        int startIndex = Arrays.asList(vertices).indexOf(start);
        //No starting vertex
        if (startIndex == -1) {
            return;
        }

        stack.push(startIndex);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited[current]) {
                visited[current] = true;
                System.out.print(vertices[current] + " ");
            }

            // push adjacent vertices (in reverse order to mimic recursive DFS order)
            for (int i = vertices.length - 1; i >= 0; i--) {
                if (matrix[current][i] == 1 && !visited[i]) {
                    stack.push(i);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Define vertex names
        String[] vertices = {"v1", "v2", "v3", "v4", "v5", "v6"};

        // Define edges (directed)
        String[][] edges = {
            {"v1", "v2"},
            {"v1", "v4"},
            {"v2", "v3"},
            {"v3", "v4"},
            {"v3", "v6"},
            {"v3", "v5"},
            {"v5", "v6"},
            {"v6", "v4"}
        };

        int[][] adjMatrix = buildAdjacencyMatrix(vertices, edges);
        printMatrix(vertices, adjMatrix);

        System.out.println();

        bfs(vertices, adjMatrix, "v1");
        dfs(vertices, adjMatrix, "v1");
    }
}
