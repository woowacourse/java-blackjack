package model.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        validateDuplicateNames(players);
        this.players = players;
    }

    private void validateDuplicateNames(List<Player> players) {
        Set<String> uniqueNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());
        if (uniqueNames.size() != players.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복되지 않아야 합니다.");
        }
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
