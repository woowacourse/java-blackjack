package model.participant;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Players {
    private final List<Player> players;

    public static Players from(final List<String> playerNames) {
        validatePlayerDuplication(playerNames);

        return new Players(playerNames.stream()
                .map(Player::new)
                .toList()
        );
    }

    public Players(final List<Player> players) {
        this.players = players;
    }

    private static void validatePlayerDuplication(final List<String> playerNames) {
        Set<String> uniqueNames = new HashSet<>(playerNames);
        if (uniqueNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름 간 중복은 허용하지 않습니다.");
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
