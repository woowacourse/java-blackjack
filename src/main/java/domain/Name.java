package domain;

public class Name {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 15;

    private final String name;

    public Name(String name) {
        validateLength(name);
        this.name = name;
    }

    private static void validateLength(final String name) {
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }
}
