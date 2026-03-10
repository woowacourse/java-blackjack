package domain.player;

import static config.BlackjackGameConstant.PLAYER_NAME_LENGTH_LIMIT;

import java.util.Objects;

public record PlayerName(String name) {

    public static PlayerName from(String name) {
        validateBlank(name);
        validateNameLength(name);
        return new PlayerName(name);
    }

    private static void validateNameLength(String name) {
        if (name.length() > PLAYER_NAME_LENGTH_LIMIT) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerName that = (PlayerName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
