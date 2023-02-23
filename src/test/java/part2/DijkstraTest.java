package part2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DijkstraTest {

    private static Graph smallGraph;
    private static Graph bigGraph;
    private static int smallGraphSize = 8;
    private static int bigGraphSize = 18;

    @BeforeEach
    public void init() {
        smallGraph = new Graph(smallGraphSize);

        var v0 = smallGraph.addVertex("0");
        var v1 = smallGraph.addVertex("1");
        var v2 = smallGraph.addVertex("2");
        var v3 = smallGraph.addVertex("3");
        var v4 = smallGraph.addVertex("4");
        var v5 = smallGraph.addVertex("5");
        var v6 = smallGraph.addVertex("6");
        var v7 = smallGraph.addVertex("7");

        smallGraph.addEdge(v6, v1, 3);
        smallGraph.addEdge(v6, v2, 7);
        smallGraph.addEdge(v6, v4, 8);
        smallGraph.addEdge(v6, v5, 2);
        smallGraph.addEdge(v6, v7, 2);
        smallGraph.addEdge(v7, v4, 5);
        smallGraph.addEdge(v7, v5, 6);
        smallGraph.addEdge(v4, v2, 9);
        smallGraph.addEdge(v4, v0, 8);

        bigGraph = new Graph(bigGraphSize);

        var g0 = bigGraph.addVertex("0");
        var g1 = bigGraph.addVertex("1");
        var g2 = bigGraph.addVertex("2");
        var g3 = bigGraph.addVertex("3");
        var g4 = bigGraph.addVertex("4");
        var g5 = bigGraph.addVertex("5");
        var g6 = bigGraph.addVertex("6");
        var g7 = bigGraph.addVertex("7");
        var g8 = bigGraph.addVertex("8");
        var g9 = bigGraph.addVertex("9");
        var g10 = bigGraph.addVertex("10");
        var g11 = bigGraph.addVertex("11");
        var g12 = bigGraph.addVertex("12");
        var g13 = bigGraph.addVertex("13");
        var g14 = bigGraph.addVertex("14");
        var g15 = bigGraph.addVertex("15");
        var g16 = bigGraph.addVertex("16");
        var g17 = bigGraph.addVertex("17");

        bigGraph.addEdge(g0, g1, 1);
        bigGraph.addEdge(g0, g2, 8);
        bigGraph.addEdge(g0, g7, 8);
        bigGraph.addEdge(g0, g4, 4);
        bigGraph.addEdge(g1, g4, 9);
        bigGraph.addEdge(g2, g5, 1);
        bigGraph.addEdge(g2, g6, 5);
        bigGraph.addEdge(g3, g10, 6);
        bigGraph.addEdge(g3, g17, 9);
        bigGraph.addEdge(g4, g7, 5);

        bigGraph.addEdge(g5, g2, 1);
        bigGraph.addEdge(g5, g6, 7);
        bigGraph.addEdge(g5, g9, 1);
        bigGraph.addEdge(g5, g8, 1);

        bigGraph.addEdge(g6, g9, 9);

        bigGraph.addEdge(g7, g8, 2);
        bigGraph.addEdge(g7, g14, 9);

        bigGraph.addEdge(g8, g9, 9);
        bigGraph.addEdge(g8, g12, 8);
        bigGraph.addEdge(g8, g11, 4);

        bigGraph.addEdge(g9, g12, 4);
        bigGraph.addEdge(g9, g10, 9);
        bigGraph.addEdge(g9, g13, 3);

        bigGraph.addEdge(g10, g17, 6);
        bigGraph.addEdge(g10, g13, 3);

        bigGraph.addEdge(g11, g15, 8);

        bigGraph.addEdge(g12, g15, 5);
        bigGraph.addEdge(g12, g16, 7);

        bigGraph.addEdge(g13, g17, 5);
        bigGraph.addEdge(g13, g16, 8);

        bigGraph.addEdge(g15, g16, 3);
    }

    @Test
    @DisplayName("Should find the right shortest paths from all of the vertices to another ones for small graph")
    public void testSmallGraphWithDifferentStartingVertex() {
        int[][] expectedAllShortestPaths = {
                {0, 18, 17, Integer.MAX_VALUE, 8, 17, 15, 13},
                {18, 0, 10, Integer.MAX_VALUE, 10, 5, 3, 5},
                {17, 10, 0, Integer.MAX_VALUE, 9, 9, 7, 9},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {8, 10, 9, Integer.MAX_VALUE, 0, 9, 7, 5},
                {17, 5, 9, Integer.MAX_VALUE, 9, 0, 2, 4},
                {15, 3, 7, Integer.MAX_VALUE, 7, 2, 0, 2},
                {13, 5, 9, Integer.MAX_VALUE, 5, 4, 2, 0}
        };

        int[][] expectedPreviousVerticesInPaths = {
                {0, 6, 4, -1, 0, 6, 7, 4},
                {4, 1, 6, -1, 7, 6, 1, 6},
                {4, 6, 2, -1, 2, 6, 2, 6},
                {-1, -1, -1, 3, -1, -1, -1, -1},
                {4, 6, 4, -1, 4, 6, 7, 4},
                {4, 6, 6, -1, 7, 5, 5, 6},
                {4, 6, 6, -1, 7, 6, 6, 6},
                {4, 6, 6, -1, 7, 6, 7, 7}
        };

        var vertices = smallGraph.getVertices();
        for (Vertex startingVertex : vertices) {
            smallGraph.setToDefault();
            smallGraph.setStartingVertex(startingVertex);

            int[] actualShortestPaths = smallGraph.printShortestPaths();
            assertArrayEquals(expectedAllShortestPaths[vertices.indexOf(startingVertex)], actualShortestPaths, "Fail at vertex " + vertices.indexOf(startingVertex));

            var actualPreviousVerticesInPaths = smallGraph.getPreviousVerticesInPaths();
            assertArrayEquals(expectedPreviousVerticesInPaths[vertices.indexOf(startingVertex)], actualPreviousVerticesInPaths);
        }
    }

    @Test
    @DisplayName("Should find the right shortest paths from all of the vertices to another ones for big graph")
    public void testBigGraphWithDifferentStartingVertex() {
        int[][] expectedAllShortestPaths = {
                {0, 1, 8, 22, 4, 9, 13, 8, 10, 10, 16, 14, 14, 13, 17, 19, 21, 18},
                {1, 0, 9, 23, 5, 10, 14, 9, 11, 11, 17, 15, 15, 14, 18, 20, 22, 19},
                {8, 9, 0, 14, 9, 1, 5, 4, 2, 2, 8, 6, 6, 5, 13, 11, 13, 10},
                {22, 23, 14, 0, 21, 13, 19, 16, 14, 12, 6, 18, 16, 9, 25, 20, 17, 9},
                {4, 5, 9, 21, 0, 8, 14, 5, 7, 9, 15, 11, 13, 12, 14, 18, 20, 17},
                {9, 10, 1, 13, 8, 0, 6, 3, 1, 1, 7, 5, 5, 4, 12, 10, 12, 9},
                {13, 14, 5, 19, 14, 6, 0, 9, 7, 7, 13, 11, 11, 10, 18, 16, 18, 15},
                {8, 9, 4, 16, 5, 3, 9, 0, 2, 4, 10, 6, 8, 7, 9, 13, 15, 12},
                {10, 11, 2, 14, 7, 1, 7, 2, 0, 2, 8, 4, 6, 5, 11, 11, 13, 10},
                {10, 11, 2, 12, 9, 1, 7, 4, 2, 0, 6, 6, 4, 3, 13, 9, 11, 8},
                {16, 17, 8, 6, 15, 7, 13, 10, 8, 6, 0, 12, 10, 3, 19, 14, 11, 6},
                {14, 15, 6, 18, 11, 5, 11, 6, 4, 6, 12, 0, 10, 9, 15, 8, 11, 14},
                {14, 15, 6, 16, 13, 5, 11, 8, 6, 4, 10, 10, 0, 7, 17, 5, 7, 12},
                {13, 14, 5, 9, 12, 4, 10, 7, 5, 3, 3, 9, 7, 0, 16, 11, 8, 5},
                {17, 18, 13, 25, 14, 12, 18, 9, 11, 13, 19, 15, 17, 16, 0, 22, 24, 21},
                {19, 20, 11, 20, 18, 10, 16, 13, 11, 9, 14, 8, 5, 11, 22, 0, 3, 16},
                {21, 22, 13, 17, 20, 12, 18, 15, 13, 11, 11, 11, 7, 8, 24, 3, 0, 13},
                {18, 19, 10, 9, 17, 9, 15, 12, 10, 8, 6, 14, 12, 5, 21, 16, 13, 0}
        };

        int[][] expectedPreviousVerticesInPaths = {
                {0, 0, 0, 10, 0, 2, 2, 0, 7, 5, 13, 8, 9, 9, 7, 12, 13, 13},
                {1, 1, 0, 10, 0, 2, 2, 0, 7, 5, 13, 8, 9, 9, 7, 12, 13, 13},
                {2, 0, 2, 10, 7, 2, 2, 8, 5, 5, 13, 8, 9, 9, 7, 12, 13, 13},
                {2, 0, 5, 3, 7, 9, 2, 8, 5, 13, 3, 8, 9, 10, 7, 16, 13, 3},
                {4, 0, 5, 10, 4, 8, 2, 4, 7, 5, 13, 8, 9, 9, 7, 12, 13, 13},
                {2, 0, 5, 10, 7, 5, 2, 8, 5, 5, 13, 8, 9, 9, 7, 12, 13, 13},
                {2, 0, 6, 10, 7, 2, 6, 8, 5, 5, 13, 8, 9, 9, 7, 12, 13, 13},
                {7, 0, 5, 10, 7, 8, 2, 7, 7, 5, 13, 8, 9, 9, 7, 12, 13, 13},
                {7, 0, 5, 10, 7, 8, 2, 8, 8, 5, 13, 8, 9, 9, 7, 12, 13, 13},
                {2, 0, 5, 10, 7, 9, 2, 8, 5, 9, 13, 8, 9, 9, 7, 12, 13, 13},
                {2, 0, 5, 10, 7, 9, 2, 8, 5, 13, 10, 8, 9, 10, 7, 16, 13, 10},
                {7, 0, 5, 10, 7, 8, 2, 8, 11, 5, 13, 11, 9, 9, 7, 11, 15, 13},
                {2, 0, 5, 10, 7, 9, 2, 8, 5, 12, 13, 8, 12, 9, 7, 12, 12, 13},
                {2, 0, 5, 10, 7, 9, 2, 8, 5, 13, 13, 8, 9, 13, 7, 16, 13, 13},
                {7, 0, 5, 10, 7, 8, 2, 14, 7, 5, 13, 8, 9, 9, 14, 12, 13, 13},
                {2, 0, 5, 10, 7, 9, 2, 8, 5, 12, 13, 15, 15, 16, 7, 15, 15, 13},
                {2, 0, 5, 10, 7, 9, 2, 8, 5, 12, 13, 15, 16, 16, 7, 16, 16, 13},
                {2, 0, 5, 17, 7, 9, 2, 8, 5, 13, 17, 8, 9, 17, 7, 16, 13, 17}
        };

        var vertices = bigGraph.getVertices();
        for (Vertex startingVertex : vertices) {
            bigGraph.setToDefault();
            bigGraph.setStartingVertex(startingVertex);

            int[] actualShortestPaths = bigGraph.printShortestPaths();
            assertArrayEquals(expectedAllShortestPaths[vertices.indexOf(startingVertex)], actualShortestPaths, "Fail at vertex " + vertices.indexOf(startingVertex));

            var actualPreviousVerticesInPaths = bigGraph.getPreviousVerticesInPaths();
            assertArrayEquals(expectedPreviousVerticesInPaths[vertices.indexOf(startingVertex)], actualPreviousVerticesInPaths, "Fail at vertex " + vertices.indexOf(startingVertex));
        }
    }

}
