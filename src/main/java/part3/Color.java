package part3;

public enum Color {
    RED("красный"),
    BACK("черный"),
    WHITE("белый"),
    BLUE("голубой"),
    YELLOW("желтый"),
    GREEN("зеленый"),
    PINK("розовый");

    private final String description;

    Color(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
