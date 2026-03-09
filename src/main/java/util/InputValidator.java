package util;

import java.util.HashSet;
import java.util.List;

public class InputValidator {
    public static void validateInputNames(List<String> playerNames) {
        validateDuplicateNames(playerNames);
        validateEmptyNames(playerNames);
        validatePlayerCounts(playerNames);
    }
    private static void validateDuplicateNames(List<String> playerNames) {
        if (playerNames.size() != (new HashSet<>(playerNames)).size()) {
            throw new IllegalArgumentException("플레이어의 이름은 중복되지 않아야 합니다.");
        }
    }

    private static void validateEmptyNames(List<String> playerNames) {
        boolean isBlank = playerNames.stream().anyMatch(String::isBlank);
        if (isBlank) {
            throw new IllegalArgumentException("플레이어의 이름은 공백이 될 수 없습니다.");
        }
    }

    private static void validatePlayerCounts(List<String> playerNames) {
        if (playerNames.isEmpty() || playerNames.size() > 8) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상 8명 이하여야 합니다.");
        }
    }
}
