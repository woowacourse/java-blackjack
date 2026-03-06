package blackjack.model;

import blackjack.exception.ErrorMessage;

public class Name {

    public static final int NAME_MIN_LENGTH = 2;
    public static final int NAME_MAX_LENGTH = 5;
    private final String name;

    public Name(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_NAME_LENGTH.getMessage());
        }
    }

    public String getName() {
        return name;
    }
}
