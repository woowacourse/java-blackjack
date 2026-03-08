package blackjack.model;

import blackjack.exception.ErrorMessage;

import java.util.Collections;
import java.util.List;

public class Players {

    public static final int PLAYERS_MAX_SIZE = 5;
    public static final int PLAYERS_MIN_SIZE = 2;
    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayersNumber(players);
        this.players = players;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validatePlayersNumber(List<Player> players) {
        if (players.size() < Players.PLAYERS_MIN_SIZE || players.size() > PLAYERS_MAX_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PLAYERS.getMessage());
        }
    }
}
