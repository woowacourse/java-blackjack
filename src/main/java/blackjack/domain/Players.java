package blackjack.domain;

import blackjack.common.ErrorMessage;
import java.util.Collections;
import java.util.List;

public class Players {

    private static final int PLAYERS_VALID_SIZE = 7;
    private final List<Player> players;

    private Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validate(List<Player> players) {
        if (players.isEmpty()){
            throw new IllegalArgumentException(ErrorMessage.NEED_PLAYER_MEMBERS.getMessage());
        }

        if (players.size() > PLAYERS_VALID_SIZE ) {
            throw new IllegalArgumentException(ErrorMessage.EXCEED_PLAYER_MEMBERS.getMessage());
        }
    }
}
