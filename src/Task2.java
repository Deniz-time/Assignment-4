import java.util.*;

public class Task2 {
    public static void runTask2() {
        Graph graph = Task1.createGraph();
        char startNode = 'E';

        System.out.print("DFS Traversal Order: ");
        Set<Character> dfsVisited = new LinkedHashSet<>();
        dfs(graph, startNode, dfsVisited);
        printVisited(dfsVisited);

        System.out.print("BFS Traversal Order: ");
        Set<Character> bfsVisited = bfs(graph, startNode);
        printVisited(bfsVisited);

        System.out.println("\n[Report Question Answer]:");
        System.out.println("Which algorithm is better suited for finding the shortest path in an unweighted graph, and why?");
        System.out.println("Answer: BFS (Breadth-First Search) is definitely better suited.");
        System.out.println("Reason: BFS explores the graph layer by layer, expanding outward uniformly. The first time BFS visits");
        System.out.println("any node, it is guaranteed to have traveled along the path with the minimum number of edges from the source.");
    }

    private static void dfs(Graph graph, char current, Set<Character> visited) {
        visited.add(current);
        List<Graph.Edge> neighbors = graph.getAdjList().getOrDefault(current, new ArrayList<>());
        List<Graph.Edge> sortedNeighbors = new ArrayList<>(neighbors);
        sortedNeighbors.sort(Comparator.comparingInt(e -> e.target));

        for (Graph.Edge edge : sortedNeighbors) {
            if (!visited.contains(edge.target)) {
                dfs(graph, edge.target, visited);
            }
        }
    }

    private static Set<Character> bfs(Graph graph, char start) {
        Set<Character> visited = new LinkedHashSet<>();
        Queue<Character> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            char current = queue.poll();
            List<Graph.Edge> neighbors = graph.getAdjList().getOrDefault(current, new ArrayList<>());
            List<Graph.Edge> sortedNeighbors = new ArrayList<>(neighbors);
            sortedNeighbors.sort(Comparator.comparingInt(e -> e.target));

            for (Graph.Edge edge : sortedNeighbors) {
                if (!visited.contains(edge.target)) {
                    visited.add(edge.target);
                    queue.add(edge.target);
                }
            }
        }
        return visited;
    }

    private static void printVisited(Set<Character> visited) {
        List<String> list = new ArrayList<>();
        for (char c : visited) {
            list.add(String.valueOf(c));
        }
        System.out.println(String.join(" -> ", list));
    }
}