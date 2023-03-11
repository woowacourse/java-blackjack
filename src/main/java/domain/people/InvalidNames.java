package domain.people;

import java.util.Arrays;

public enum InvalidNames {
    DEALER("딜러");

    private final String name;

    InvalidNames(String name) {
        this.name = name;
    }

    public static boolean isInvalidNames(String name) {
        return Arrays.stream(InvalidNames.values()).anyMatch(invalidName -> invalidName.name().equals(name));
    }
}
