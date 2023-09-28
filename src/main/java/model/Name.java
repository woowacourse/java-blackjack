package model;

public class Name {

    private final String name;

    private Name(final String name) {
        this.name = name;
    }

    public static Name from(final String name) {
        return new Name(name);
    }

    public String getName() {
        return name;
    }
}
