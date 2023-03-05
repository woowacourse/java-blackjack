package domain.player;

public class Name {
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 15;

    private final String name;

    public Name(final String name) {
        validateLength(name);
        this.name = name;
    }

    private static void validateLength(final String name) {
        if (name.length() < MIN_LENGTH || MAX_LENGTH < name.length()) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }
}
