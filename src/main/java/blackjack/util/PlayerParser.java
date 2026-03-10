package blackjack.util;

import blackjack.model.user.Player;
import java.util.Arrays;
import java.util.List;

public class PlayerParser {

    public static List<Player> parse(String names) {
        return Arrays.stream(names.split(","))
                .map(String::trim)
                .map(Player::new)
                .toList();
    }
}
