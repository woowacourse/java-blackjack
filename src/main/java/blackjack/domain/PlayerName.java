package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerName {
    private final String value;

    public PlayerName(String value) {
        this.value = value;
    }

    public static List<PlayerName> from(List<String> playerName) {
        return playerName.stream()
                .map(PlayerName::new)
                .collect(Collectors.toList());
    }

    public String getValue() {
        return value;
    }
}
