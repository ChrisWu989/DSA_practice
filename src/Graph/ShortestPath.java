package Graph;

import java.util.Arrays;
//Shortest path problem (Dijkstra's algo which is greedy)
//adjacency matrix but use lengths
//distance array tracks shortest distances from vertex v1 to all other vertices
//final array starting vertex
public class ShortestPath {
    static int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    static void dijkstra(int[][] graph, int starting){
        int v = graph.length;
        int[] dist = new int[v];                // shortest distance from starting vertex
        boolean[] visited = new boolean[v];     // if vertex has been visited (same as final)

        for (int i = 1; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[starting] = 0; //starting point

        for (int i = 0; i < v ; i++){

            int x = minDistance(dist, visited); //calc distance for min distance not yet visited
            if (x == -1){ //NO reachable vertex
                break;
            }
            visited[x] = true; // visited this node

            for (int e = 0; e < v; e++){
                if (!visited[e] && graph[x][e] != 0 && // edge exists
                    dist[x] != Integer.MAX_VALUE && // no infinity
                    dist[x] + graph[x][e] < dist[e]) { //distance is short
                        dist[e] = dist[x] + graph[x][e];
                }
            }
        }
        printSolution(dist, visited);
    }

    static void printSolution(int[] dist, boolean[] visited) {
        System.out.println("Vertex\tDistance\tVisited");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + "\t" + dist[i] + "\t\t" + visited[i]);
        }
    }
    public static void main(String[] args) {
        // Graph represented as adjacency matrix
        int[][] graph = {
            {0, 5, 0, 3, 0, 0},
            {0, 0, 4, 0, 0, 6},
            {0, 0, 0, 0, 3, 3},
            {0, 0, 2, 0, 6, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };

        int starting = 0; // starting vertex 
        dijkstra(graph, starting);
    }
}
