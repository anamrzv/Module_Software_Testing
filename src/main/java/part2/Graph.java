package part2;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {
    private final int MAX_VERTS;
    private final ArrayList<Vertex> vertices;
    private final int[][] connections;
    private int[] shortestPaths;
    private int startingVertexIndex = -1;

    public Graph(int vertexNum) {
        if (vertexNum <= 0) throw new IllegalArgumentException("В графе должна быть хотя бы одна вершина");
        MAX_VERTS = vertexNum;
        vertices = new ArrayList<>();
        connections = new int[MAX_VERTS][MAX_VERTS];
        shortestPaths = new int[MAX_VERTS];
        for (int j = 0; j < MAX_VERTS; j++) {
            for (int k = 0; k < MAX_VERTS; k++) {
                connections[j][k] = 0;
            }
            shortestPaths[j] = Integer.MAX_VALUE;
        }
    }

    private void setStartingVertex(Vertex from) {
        int index = vertices.indexOf(from);
        shortestPaths[index] = 0;
        from.setLastVertexIndex(index);
        startingVertexIndex = index;
    }

    public void addEdge(Vertex from, Vertex to, int weight) {
        int start = vertices.indexOf(from);
        int end = vertices.indexOf(to);
        connections[start][end] = weight;
        connections[end][start] = weight;
    }

    public String displayVertex(int v) {
        return vertices.get(v).getName();
    }

    public Vertex addVertex(String name) {
        Vertex v = new Vertex(name);
        vertices.add(v);
        return v;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public int[] getPreviousVerticesInPaths() {
        int[] previousVerticesInPaths = new int[MAX_VERTS];
        for (int i = 0; i < MAX_VERTS; i++) {
            previousVerticesInPaths[i] = vertices.get(i).getLastVertexIndex();
        }
        return previousVerticesInPaths;
    }

    private void setToDefault() {
        vertices.forEach((v) -> v.setInvestigated(false));
        vertices.forEach((v) -> v.setLastDistance(-1));
        vertices.forEach((v) -> v.setLastVertexIndex(-1));

        for (int j = 0; j < MAX_VERTS; j++) {
            shortestPaths[j] = Integer.MAX_VALUE;
        }
    }

    public void dijkstra(PriorityQueue<Vertex> priorityQueue) {
        while (!priorityQueue.isEmpty()) {
            Vertex currentVertex = priorityQueue.poll();
            if (currentVertex.isInvestigated()) continue;
            int currentVertexIndex = vertices.indexOf(currentVertex);
            vertices.get(currentVertexIndex).setInvestigated(true);

            for (int v = 0; v < MAX_VERTS; v++) {
                if (v != currentVertexIndex && connections[currentVertexIndex][v] != 0 && !vertices.get(v).isInvestigated()) {
                    Vertex neighbourVertex = vertices.get(v);
                    if (connections[currentVertexIndex][v] + shortestPaths[currentVertexIndex] < shortestPaths[v]) {
                        shortestPaths[v] = connections[currentVertexIndex][v] + shortestPaths[currentVertexIndex];
                        vertices.get(v).setLastVertexIndex(currentVertexIndex);
                        neighbourVertex.setLastDistance(shortestPaths[v]);
                        priorityQueue.add(neighbourVertex);
                    }
                }
            }

            dijkstra(priorityQueue);
        }
    }

    public int[] printShortestPaths(Vertex from) {
        if (from == null) throw new IllegalArgumentException("Начальная вершина не проинициализирована");
        if (!vertices.contains(from)) throw new IllegalArgumentException("Начальная вершина не содержится в графе");
        if (MAX_VERTS != vertices.size())
            throw new IllegalStateException("Число созданных вершин не совпадает с заявленным");

        setToDefault();
        setStartingVertex(from);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(new VertexComparator());
        priorityQueue.add(vertices.get(startingVertexIndex));
        dijkstra(priorityQueue);
        System.out.println("Кратчайшие пути от вершины " + displayVertex(startingVertexIndex) + ":\n");
        for (int i = 0; i < MAX_VERTS; i++) {
            String length = shortestPaths[i] != Integer.MAX_VALUE ? String.valueOf(shortestPaths[i]) : "Нет пути";
            System.out.println("Длина пути до вершины " + displayVertex(i) + ": " + length);
            String path = shortestPaths[i] != Integer.MAX_VALUE ? getVertexShortestPath(displayVertex(i), vertices.get(i).getLastVertexIndex()) : "Нет пути";
            System.out.println("Путь: " + path + "\n");
        }
        return shortestPaths;
    }

    private String getVertexShortestPath(String path, int index) {
        path = displayVertex(index) + "->" + path;
        if (index == vertices.get(index).getLastVertexIndex()) return path;
        return getVertexShortestPath(path, vertices.get(index).getLastVertexIndex());
    }
}

