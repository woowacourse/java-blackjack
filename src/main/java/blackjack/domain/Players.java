package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private static final String PLAYER_NUMBER_ERROR_MESSAGE = "[ERROR] 플레이어 수는 1~8명 사이여야 합니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayerNumber(players);
        this.players = new ArrayList<>(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validatePlayerNumber(List<Player> players) {
        if (players.size() < 1 || players.size() > 8) {
            throw new IllegalArgumentException(PLAYER_NUMBER_ERROR_MESSAGE);
        }
    }
}
