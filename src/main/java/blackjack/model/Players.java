package blackjack.model;

import java.util.List;

public class Players {
    private static final String INVALID_NAMES_COUNT = "참여자 수는 1명 이상이다";

    private final List<Player> players;

    public Players(final List<String> playerNames) {
        validatePlayerNamesCount(playerNames);
        this.players = playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private void validatePlayerNamesCount(final List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAMES_COUNT);
        }
    }
}
