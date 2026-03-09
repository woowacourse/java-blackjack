package domain.player;

import static config.BlackjackGameConstant.PLAYER_NAME_LENGTH_LIMIT;

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
}
