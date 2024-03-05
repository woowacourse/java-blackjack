package blackjack.model;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(final List<Player> players) {
        if (players.size() < 1 || players.size() > 10) {
            throw new IllegalArgumentException("참여할 인원의 수는 최소 1명 최대 10명이어야 합니다.");
        }
        this.players = players;
    }

    public static Players from(final List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }
}
