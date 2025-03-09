package blackjack.util;

import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerNameParser {

    private PlayerNameParser() {
    }

    private static final String PLAYER_NAME_DELIMITER = ",";

    public static List<Player> parseNames(String namesInput) {
        List<String> playerNames = List.of(namesInput.split(PLAYER_NAME_DELIMITER));

        return playerNames.stream()
                .map(Gambler::new)
                .collect(Collectors.toList());
    }
}
