package domain;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class PlayerNames {
    private final List<PlayerName> playerNames;

    private PlayerNames(List<PlayerName> playerNames) {
        this.playerNames = playerNames;
    }

    public static PlayerNames of(List<String> playerNames) {
        return playerNames.stream()
                .map(PlayerName::new)
                .collect(collectingAndThen(toList(), PlayerNames::new));
    }
}
