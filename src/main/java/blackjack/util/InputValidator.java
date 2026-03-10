package blackjack.util;

import blackjack.common.error.ErrorCode;
import java.util.HashSet;
import java.util.List;

public final class InputValidator {

    public static void validatePlayerNames(final List<String> names) {
        validateInvalidName(names);
        validateUniqueNames(names);
    }

    private static void validateInvalidName(List<String> names) {
        boolean hasInvalidName = names.stream()
                .anyMatch(name -> name.startsWith(" ") || name.endsWith(" "));

        if (hasInvalidName) {
            throw new IllegalArgumentException(ErrorCode.NAME_STARTS_OR_ENDS_WITH_SPACE.getMessage());
        }
    }

    private static void validateUniqueNames(List<String> names) {
        if (new HashSet<>(names).size() < names.size()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATED_NAME.getMessage());
        }
    }
}
