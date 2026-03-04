package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    private final Set<Player> players = new HashSet<>();

    public Game(List<String> names) {
        validateDuplicatedName(names);

        players.addAll(names.stream()
                .map(Player::new)
                .collect(Collectors.toSet()));
    }

    private void validateDuplicatedName(List<String> names) {
        Set<String> distinctNames = Set.copyOf(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public Set<Player> getPlayers() {
        return Set.copyOf(players);
    }
}
