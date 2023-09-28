package model;

public class Name {

    private final String name;

    private Name(final String name) {
        this.name = name;
    }

    public static Name from(final String name) {
        return new Name(name);
    }

    public static Name withScore(final int score, final String name) {
        return new Name(score + name);
    }

    public String getName() {
        return name;
    }
}
