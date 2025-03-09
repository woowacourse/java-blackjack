package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> names) {
        validateDuplication(names);
        return new Players(
                names.stream()
                        .map(Player::new)
                        .toList()
        );
    }

    public void dealInitialCards(Deck deck) {
        players.forEach(player ->
                player.dealInitialCards(deck)
        );
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private static void validateDuplication(List<String> playerNames) {
        Set<String> uniqueNames = new HashSet<>(playerNames);
        if (uniqueNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름 간 중복은 허용하지 않습니다.");
        }
    }
}
