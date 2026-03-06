package blackjack.domain.participant;

import java.util.List;

public class Players {

    private static final String EMPTY_PLAYERS_MESSAGE = "플레이어는 1명 이상이어야 합니다.";
    private static final String DUPLICATE_NAME_MESSAGE = "중복된 이름의 플레이어가 있습니다.";

    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = List.copyOf(players);
    }

    private void validate(final List<Player> players) {
        validateNotEmpty(players);
        validateNoDuplicateNames(players);
    }

    private void validateNotEmpty(final List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_PLAYERS_MESSAGE);
        }
    }

    private void validateNoDuplicateNames(final List<Player> players) {
        final long uniqueCount = players.stream()
                .map(Player::getName)
                .distinct()
                .count();
        if (uniqueCount != players.size()) {
            throw new IllegalArgumentException(DUPLICATE_NAME_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
