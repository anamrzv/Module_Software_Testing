package part2;

public class Vertex {
    private String name;
    private boolean isInvestigated;
    private int lastVertexIndex;
    private int lastDistance;

    public Vertex(final String name) {
        this.name = name;
        isInvestigated = false;
        lastVertexIndex = -1;
        lastDistance = -1;
    }

    public String getName() {
        return this.name;
    }

    public boolean isInvestigated() {
        return this.isInvestigated;
    }

    public void setInvestigated(final boolean investigated) {
        this.isInvestigated = investigated;
    }

    public int getLastVertexIndex() {
        return lastVertexIndex;
    }

    public void setLastVertexIndex(int lastVertexIndex) {
        this.lastVertexIndex = lastVertexIndex;
    }

    public int getLastDistance() {
        return lastDistance;
    }

    public void setLastDistance(int lastDistance) {
        this.lastDistance = lastDistance;
    }
}