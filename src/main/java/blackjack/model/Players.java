package blackjack.model;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final int MINIMUM_PLAYER_SIZE = 1;
    private static final int MAXIMUM_PLAYER_SIZE = 10;

    private final List<Player> players;

    private Players(final List<Player> players) {
        validatePlayerSize(players);
        this.players = players;
    }

    public static Players from(final List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

    private void validatePlayerSize(final List<Player> players) {
        if (players.size() < MINIMUM_PLAYER_SIZE || players.size() > MAXIMUM_PLAYER_SIZE) {
            throw new IllegalArgumentException("참여할 인원의 수는 최소 1명 최대 10명이어야 합니다.");
        }
    }
}
