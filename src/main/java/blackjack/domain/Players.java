package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Players {

    private static final int PLAYERS_VALID_SIZE = 7;
    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validate(List<Player> players) {
        if (players.size() > PLAYERS_VALID_SIZE) {
            throw new IllegalArgumentException("7명을 초과할 수 없습니다.");
        }
    }


}
