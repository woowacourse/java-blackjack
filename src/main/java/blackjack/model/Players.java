package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private static final int MINIMUM_PLAYER_SIZE = 1;
    private static final int MAXIMUM_PLAYER_SIZE = 10;

    private final List<Player> players;

    private Players(final List<Player> players) {
        validateNotDuplicateName(players);
        validatePlayerSize(players);
        this.players = players;
    }

    public static Players from(final List<String> names, final List<Cards> cards) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), cards.get(i)));
        }
        return new Players(players);
    }

    private void validateNotDuplicateName(final List<Player> players) {
        long uniqueNameSize = players.stream()
                .distinct()
                .count();
        if (players.size() != uniqueNameSize) {
            throw new IllegalArgumentException("중복되는 이름을 입력할 수 없습니다.");
        }
    }

    private void validatePlayerSize(final List<Player> players) {
        if (players.size() < MINIMUM_PLAYER_SIZE || players.size() > MAXIMUM_PLAYER_SIZE) {
            throw new IllegalArgumentException("참여할 인원의 수는 최소 1명 최대 10명이어야 합니다.");
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
