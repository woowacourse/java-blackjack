package blackjack.domain;

import blackjack.exception.ErrorMessage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Players {
    private static final String DELIMITER = ",";
    private static final int PLAYERS_MAX_LENGTH = 5;
    private static final int PLAYERS_MIN_LENGTH = 2;

    private final List<Player> players;

    public static Players from(String names) {
        List<Player> players = split(names).stream()
                .map(Player::new)
                .toList();
        return new Players(players);
    }

    public Players(List<Player> players) {
        validatePlayersNumber(players);
        this.players = players;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validatePlayersNumber(List<Player> players) {
        if (players.size() < Players.PLAYERS_MIN_LENGTH || players.size() > PLAYERS_MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PLAYERS.getMessage());
        }
    }

    private static List<String> split(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }
}
