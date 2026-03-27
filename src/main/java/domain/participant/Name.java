package domain.participant;

import exception.NameOutOfRangeException;

public class Name {
    private final String name;
    private static final int NAME_MIN_LENGTH = 1;
    private static final int NAME_MAX_LENGTH = 10;

    public Name(String name) {
        validate(name);
        this.name = name.strip();
    }

    private void validate(String name) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new NameOutOfRangeException(NAME_MIN_LENGTH, NAME_MAX_LENGTH);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
