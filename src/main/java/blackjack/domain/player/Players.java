package blackjack.domain.player;

import blackjack.domain.player.exception.DuplicatedPlayerNameException;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> names) {
        validateDuplicatedNames(names);
        List<Player> players = names.stream()
                .map(Challenger::new)
                .collect(Collectors.toUnmodifiableList());
        return new Players(players);
    }

    private static void validateDuplicatedNames(List<String> names) {
        long distinctNameCount = names.stream()
                .distinct()
                .count();

        if (names.size() != distinctNameCount) {
            throw new DuplicatedPlayerNameException();
        }
    }
}
