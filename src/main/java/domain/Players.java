package domain;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class Players {
    private static final int UNIQUE_COUNT = 1;

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validateDuplicatedName(playerNames);
        this.players = generatePlayers(playerNames);
    }

    private void validateDuplicatedName(List<String> playerNames) {
        Map<String, Long> nameCounts = getNameCounts(playerNames);
        Optional<String> duplicatedName = findDuplicatedName(nameCounts);
        if (duplicatedName.isPresent()) {
            throw new IllegalArgumentException(duplicatedName.get());
        }
    }

    private Optional<String> findDuplicatedName(Map<String, Long> nameCounts) {
        return nameCounts.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > UNIQUE_COUNT)
                .map(Entry::getKey)
                .findFirst();
    }

    private Map<String, Long> getNameCounts(List<String> playerNames) {
        return playerNames.stream()
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));
    }

    private List<Player> generatePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }
}
