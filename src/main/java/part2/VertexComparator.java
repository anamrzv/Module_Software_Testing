package part2;

import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex> {
    public int compare(Vertex v1, Vertex v2) {
        return Integer.compare(v1.getLastDistance(), v2.getLastDistance());
    }
}
