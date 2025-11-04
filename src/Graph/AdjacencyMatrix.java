package Graph;

import java.util.HashMap;
import java.util.Map;

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
    }
}
