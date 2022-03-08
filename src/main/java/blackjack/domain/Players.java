package blackjack.domain;

import java.util.List;

public class Players {

    private static final int MAX_PLAYER = 10;
    private static final String TOO_MANY_PLAYER_ERROR_MESSAGE = "최대 플레이어 수는 " + MAX_PLAYER + "명 입니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        validateSize(players);

        this.players = players;
    }

    private void validateSize(List<Player> players) {
        if (players.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(TOO_MANY_PLAYER_ERROR_MESSAGE);
        }
    }
}
