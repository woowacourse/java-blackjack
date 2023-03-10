package blackjack.domain.participant;

import java.util.List;

public final class Players {

    private static final int MIN_PLAYERS_COUNT = 2;
    private static final int MAX_PLAYERS_COUNT = 8;
    static final String PLAYERS_COUNT_ERROR_MESSAGE = "플레이어는 최소 " + MIN_PLAYERS_COUNT + "명에서 최대 " + MAX_PLAYERS_COUNT + "명입니다. 입력값: ";

    private final List<Player> players;

    public Players(final List<Player> players) {
        isValidCount(players);
        this.players = players;
    }

    private void isValidCount(final List<Player> players) {
        if (players.size() < MIN_PLAYERS_COUNT || players.size() > MAX_PLAYERS_COUNT) {
            throw new IllegalArgumentException(PLAYERS_COUNT_ERROR_MESSAGE + players);
        }
    }
}
