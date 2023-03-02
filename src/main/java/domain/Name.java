package domain;

public class Name {

    private final String name;

    public Name(String name) {
        validateLength(name);
        this.name = name;
    }

    private static void validateLength(final String name) {
        if (name.length() < 1 || name.length() > 15) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }
}
