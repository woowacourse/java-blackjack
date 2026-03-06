package domain.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerParser {
    private static final String ERROR_PLAYER_NAMES_EMPTY = "입력이 비어있습니다.";
    private static final String DELIMITER = ",";

    public static Players parseToPlayers(String rawPlayerNames) {
        validateRawPlayerNames(rawPlayerNames);

        List<String> splitedPlayerNames = splitByDelimiter(rawPlayerNames);

        return new Players(new ArrayList<>());
    }

    private static List<String> splitByDelimiter(String rawPlayerName) {
        return Arrays.stream(rawPlayerName.split(DELIMITER, -1))
                .toList();
    }

    private static void validateRawPlayerNames(String rawPlayerName) {
        validateNotBlank(rawPlayerName);
    }

    private static void validateNotBlank(String rawPlayerName) {
        if (rawPlayerName == null || rawPlayerName.isBlank()) {
            throw new IllegalArgumentException(ERROR_PLAYER_NAMES_EMPTY);
        }
    }
}
