package blackjack.util;

import static blackjack.model.ErrorMessage.ERROR_EMPTY_INPUT;
import static blackjack.model.ErrorMessage.ERROR_INVALID_PLAYER_NAME;

import blackjack.model.Player;
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
