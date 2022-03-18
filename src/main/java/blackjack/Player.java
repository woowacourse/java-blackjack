package blackjack;

import java.util.Objects;

public class Player {
    private static final String BLANK_NAME_EXCEPTION = "[ERROR] 이름이 공백입니다.";
    private String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player generate(String name) {
        Objects.requireNonNull(name);
        validateName(name);
        return new Player(name);
    }

    public static void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_EXCEPTION);
        }
    }

    public String getName() {
        return name;
    }
}
