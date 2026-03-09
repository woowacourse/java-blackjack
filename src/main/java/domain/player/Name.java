package domain.player;

import util.ErrorMessage;

public class Name {
    private static final int MIN_NAME_SIZE = 2;
    private static final int MAX_NAME_SIZE = 7;

    private final String name;

    public Name(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_SIZE || name.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NAME.getMessage());
        }
    }

    public String getName() {
        return name;
    }
}
