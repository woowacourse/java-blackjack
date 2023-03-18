package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Players {

    private static final int MIN_PLAYER_COUNT = 2;
    private static final int MAX_PLAYER_COUNT = 8;

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public static Players from(final Map<String, Integer> players) {
        validatePlayersCount(players);

        return new Players(
                players.keySet()
                .stream()
                .map(player -> new Player(player, new Money(players.get(player))))
                .collect(Collectors.toList())
        );
    }

    private static void validatePlayersCount(final Map<String, Integer> names) {
        if (names.size() < MIN_PLAYER_COUNT || names.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(
                    "[ERROR] 참가자의 수는 최소 " + MIN_PLAYER_COUNT + "명에서 최대 "
                            + MAX_PLAYER_COUNT + "명이어야 합니다.");
        }
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
