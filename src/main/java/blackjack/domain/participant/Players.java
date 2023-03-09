package blackjack.domain.participant;

import java.util.Collections;
import java.util.List;

public class Players {
    private static final String PLAYER_COUNT_ERROR_MESSAGE = "플레이어 수는 1명 이상 7명 이하여야 합니다.";
    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 7;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayerCount(players);
        this.players = players;
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.size() < MIN_PLAYER_COUNT || players.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(PLAYER_COUNT_ERROR_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
