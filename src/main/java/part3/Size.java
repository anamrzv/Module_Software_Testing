package part3;

public enum Size {
    PLATE_SIZE("Размером с тарелку"),
    BIG("Большая"),
    SMALL("Маленькая");

    private final String description;

    Size(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
