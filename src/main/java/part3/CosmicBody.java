package part3;

public abstract class CosmicBody implements Cosmic {
    private Color color;
    private Size size;

    private String name;

    public CosmicBody(Color color, Size size, String name) {
        this.color = color;
        this.size = size;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color.getDescription();
    }

    public String getSize() {
        return size.getDescription();
    }
}
