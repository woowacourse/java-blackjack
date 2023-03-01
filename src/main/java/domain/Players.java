package domain;

import static java.util.stream.Collectors.collectingAndThen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> names) {
        validate(names);
        return names.stream()
            .map(Player::new)
            .collect(collectingAndThen(Collectors.toList(), Players::new));
    }

    private static void validate(List<String> names) {
        validateDuplication(names);
        validateSize(names);
    }

    private static void validateDuplication(List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateSize(List<String> names) {
        if (names.size() < 1 || names.size() > 4) {
            throw new IllegalArgumentException();
        }
    }
}
