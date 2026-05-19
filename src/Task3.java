import java.util.*;

public class Task3 {
    public static class NodePair implements Comparable<NodePair> {
        char vertex;
        int distance;

        public NodePair(char vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodePair other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static void runTask3() {
        Graph graph = Task1.createGraph();
        char source = 'B';

        Map<Character, Integer> distances = new HashMap<>();
        Map<Character, Character> predecessors = new HashMap<>();
        PriorityQueue<NodePair> minHeap = new PriorityQueue<>();

        // Инициализация бесконечностями
        for (char vertex : graph.getAdjList().keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
            predecessors.put(vertex, null);
        }

        distances.put(source, 0);
        minHeap.add(new NodePair(source, 0));

        while (!minHeap.isEmpty()) {
            NodePair current = minHeap.poll();
            char u = current.vertex;
            int distU = current.distance;

            if (distU > distances.get(u)) continue;

            for (Graph.Edge edge : graph.getAdjList().getOrDefault(u, new ArrayList<>())) {
                char v = edge.target;
                int weight = edge.weight;

                if (distances.get(u) + weight < distances.get(v)) {
                    distances.put(v, distances.get(u) + weight);
                    predecessors.put(v, u);
                    minHeap.add(new NodePair(v, distances.get(v)));
                }
            }
        }

        // Сортируем вершины для красивого вывода результатов
        List<Character> vertices = new ArrayList<>(graph.getAdjList().keySet());
        Collections.sort(vertices);

        for (char target : vertices) {
            System.out.print("To " + target + " -> Distance: " + distances.get(target) + ", Path: ");
            if (distances.get(target) == Integer.MAX_VALUE) {
                System.out.println("Unreachable");
            } else {
                System.out.println(getPathExpression(target, predecessors));
            }
        }
    }

    private static String getPathExpression(char target, Map<Character, Character> predecessors) {
        List<String> path = new ArrayList<>();
        Character curr = target;
        while (curr != null) {
            path.add(String.valueOf(curr));
            curr = predecessors.get(curr);
        }
        Collections.reverse(path);
        return String.join(" -> ", path);
    }
}