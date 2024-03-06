package model.player;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

public class Players {

    private static final String INVALID_PLAYERS_SIZE = "플레이어 수는 1명 이상이어야 합니다.";

    private final List<Player> players;

    private Players(List<Player> players) {
        validateEmptyPlayers(players);
        this.players = players;
    }

    public static Players from(List<String> playerNames) {
        return playerNames.stream()
            .map(Player::new)
            .collect(collectingAndThen(toList(), Players::new));
    }

    private void validateEmptyPlayers(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException(INVALID_PLAYERS_SIZE);
        }
    }
}
