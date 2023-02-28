package domain.player;

import java.util.Objects;

public class PlayerName {

    private static final int MAX_LENGTH = 20;

    private final String name;

    private PlayerName(final String name) {
        String trimName = name.trim();
        validateBlankName(trimName);
        validateNameLength(name);
        this.name = trimName;
    }

    public static PlayerName create(final String name) {
        return new PlayerName(name);
    }

    private void validateBlankName(final String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    private void validateNameLength(final String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 1글자에서 20글자 사이여야 합니다.");
        }
    }
}
