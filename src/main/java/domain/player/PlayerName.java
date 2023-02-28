package domain.player;

import java.util.Objects;

public class PlayerName {

    private final String name;

    private PlayerName(final String name) {
        String trimName = name.trim();
        validateBlankName(trimName);
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
}
