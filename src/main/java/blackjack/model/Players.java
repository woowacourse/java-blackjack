package blackjack.model;

import blackjack.exception.ErrorCode;

import java.util.Collections;
import java.util.List;

public class Players {

    public static final int PLAYERS_MAX_LENGTH = 5;
    public static final int PLAYERS_MIN_LENGTH = 2;
    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayersNumber(players);
        this.players = players;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validatePlayersNumber(List<Player> players) {
        if (players.size() < Players.PLAYERS_MIN_LENGTH || players.size() > PLAYERS_MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorCode.INVALID_PLAYERS);
        }
    }
}
