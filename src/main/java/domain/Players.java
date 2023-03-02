package domain;

import static java.util.stream.Collectors.*;

import java.util.List;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        validateNotEmpty(players);
        this.players = players;
    }

    private void validateNotEmpty(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("참여자는 1명 이상이어야 합니다.");
        }
    }

    public static Players from(List<String> rawNames) {
        return rawNames.stream()
                .map(Player::new)
                .collect(collectingAndThen(toUnmodifiableList(), Players::new));
    }
}
