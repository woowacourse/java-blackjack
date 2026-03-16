package domain.participant;

import domain.CommonExceptionMessage;
import java.util.List;

public class Players {
    private static final String ALREADY_EXIST_NAME = "[ERROR] 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        validate(players);
        return new Players(List.copyOf(players));
    }

    private static void validate(List<Player> players) {
        validateIsNotNull(players);
        validateIsNotUnique(players);
    }

    private static void validateIsNotNull(List<Player> players) {
        if (players == null) {
            throw new IllegalArgumentException(CommonExceptionMessage.FIELD_CAN_NOT_BE_NULL.getMessage());
        }
    }

    private static void validateIsNotUnique(List<Player> players) {
        long count = players.stream()
                .map(Player::getName)
                .distinct()
                .count();
        if (players.size() != count) {
            throw new IllegalArgumentException(ALREADY_EXIST_NAME);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
