package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Players {

    private static final int PLAYERS_MINIMUM_SIZE = 1;
    private static final int PLAYERS_MAXIMUM_SIZE = 7;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayers(players);
        this.players = players;
    }

    private void validatePlayers(List<Player> players) {
        if (players.size() < PLAYERS_MINIMUM_SIZE || players.size() > PLAYERS_MAXIMUM_SIZE) {
            throw new IllegalArgumentException(String.format("플레이어 인원 수는 %d명 이상 %d명 이하여야 합니다.",
                PLAYERS_MINIMUM_SIZE, PLAYERS_MAXIMUM_SIZE));
        }
    }

    public List<Player> all() {
        return Collections.unmodifiableList(players);
    }
}
