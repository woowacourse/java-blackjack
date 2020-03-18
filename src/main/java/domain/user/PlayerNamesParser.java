package domain.user;

import java.util.Arrays;
import java.util.List;

public class PlayerNamesParser {

    private static final String DELIMITER = ",";
    static final String NOT_ALPHABETIC_ERROR = "문자 형식이어야 합니다.";

    public static List<String> parse(String playerNamesValue) {
        List<String> playerNames = Arrays.asList(playerNamesValue.split(DELIMITER));
        for(String playerName : playerNames) {
            if(isNotAlphabetic(playerName)) {
                throw new IllegalArgumentException(NOT_ALPHABETIC_ERROR);
            }
        }

        return playerNames;
    }

    private static boolean isNotAlphabetic(final String playerName) {
        return playerName.chars()
                .anyMatch(c -> !Character.isAlphabetic(c));
    }
}
