package blackjack.domain;

import java.util.Arrays;
import java.util.List;

public class Players {
    private final List<Player> values;

    public Players(List<Player> values) {
        this.values = values;
    }

    public static Players from(String names) {
        return new Players(parseNames(names).stream().map(Player::new).toList());
    }

    private static List<String> parseNames(String names) {
        return Arrays.stream(names.split(",")).toList();
    }

    public List<String> getNames() {
        return values.stream().map(Player::getName).toList();
    }
}
