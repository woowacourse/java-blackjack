package blackjack.util;

import java.util.Arrays;
import java.util.List;

public class PlayerNameParser {

    private static final String DELIMITER = ",";

    public static List<String> parsePlayerNames(String playerNamesInput) {
        return Arrays.stream(playerNamesInput.split(DELIMITER)).map(String::trim).toList();
    }
}
