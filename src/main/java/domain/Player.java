package domain;

public class Player {

    private static final int NAME_MAX_LENGTH = 5;

    private final String name;

    public Player(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        if (name.isBlank() || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}
