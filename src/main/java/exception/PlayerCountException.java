package exception;

import domain.gamer.Players;

public class PlayerCountException extends IllegalArgumentException {
    public static final String INVALID_PLAYER_COUNT = String.format("플레이어는 %d명에서 %d명까지만 참가 가능합니다.",
            Players.MINIMUM_PLAYER_COUNT, Players.MAXIMUM_PLAYER_COUNT);

    public PlayerCountException(final String message) {
        super(message);
    }
}
