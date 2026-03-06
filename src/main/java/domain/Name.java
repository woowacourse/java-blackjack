package domain;

import static exception.ErrorMessage.PLAYER_NAME_LENGTH_ERROR;

public class Name {

    private final String name;

    public Name(String name) {
        if (name.length() < 2 || name.length() > 10) {
            throw new IllegalArgumentException(PLAYER_NAME_LENGTH_ERROR.getMessage());
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
