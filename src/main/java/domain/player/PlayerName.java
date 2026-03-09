package domain.player;

import static domain.config.BlackjackGameConstant.PLAYER_NAME_LENGTH_LIMIT;

public record PlayerName(String name) {

    public static PlayerName from(String name) {
        validateNameLength(name);
        return new PlayerName(name);
    }

    private static void validateNameLength(String name) {
        if (name.length() > PLAYER_NAME_LENGTH_LIMIT) {
            throw new IllegalArgumentException();
        }
    }

}
