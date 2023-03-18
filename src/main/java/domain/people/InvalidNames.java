package domain.people;

import java.util.Arrays;

public enum InvalidNames {
    DEALER("딜러"),
    BAD_WORD("심한 욕");

    private final String name;

    InvalidNames(final String name) {
        this.name = name;
    }

    public static boolean isInvalidNames(final String name) {
        return Arrays.stream(InvalidNames.values()).anyMatch(invalidName -> invalidName.name.equals(name));
    }
}
