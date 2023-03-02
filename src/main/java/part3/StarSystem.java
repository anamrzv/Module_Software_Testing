package part3;

import java.util.HashSet;
import java.util.Set;

public class StarSystem {
    private final Set<Star> stars;
    private final String name;
    private final int maxSize;

    public StarSystem(String name, int maxSize) {
        if (maxSize < 0) throw new RuntimeException("Звездная система не может иметь отричательный размер");
        this.name = name;
        this.maxSize = maxSize;
        stars = new HashSet<>();
    }

    public void addStar(Star newStar) {
        if (stars.contains(newStar))
            System.out.printf("Звезда %s уже есть в звездной системе %s%n", newStar.getName(), name);
        else if (stars.size() == maxSize)
            System.out.printf("Звездная система %s не может вместить звезду %s%n", name, newStar.getName());
        else {
            stars.add(newStar);
            System.out.printf("Звезда %s теперь есть в звездной системе %s%n", newStar.getName(), name);
        }
    }

    public int getSize() {
        return stars.size();
    }

    public Set<Star> getStars() {
        return stars;
    }
}
