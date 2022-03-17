package blackjack.domain.participant.validation;

import java.util.Collections;
import java.util.List;

import blackjack.domain.participant.condition.PlayerCount;

public class PlayerNameValidator {

    public static void validatePlayerName(final List<String> playerNames) {
        validateNameNotDuplicated(playerNames);
        validateNameCountEnough(playerNames);
    }

    private static void validateNameNotDuplicated(final List<String> playerNames) {
        if (isPlayerNameDuplicated(playerNames)) {
            throw new IllegalArgumentException("플레이어명은 중복될 수 없습니다.");
        }
    }

    private static boolean isPlayerNameDuplicated(final List<String> playerNames) {
        return playerNames.stream()
                .anyMatch(playerName -> Collections.frequency(playerNames, playerName) > 1);
    }

    private static void validateNameCountEnough(final List<String> playerNames) {
        if (PlayerCount.isCountInRange(playerNames.size())) {
            throw new IllegalArgumentException("플레이어는 8명 이하여야 합니다.");
        }
    }

}
