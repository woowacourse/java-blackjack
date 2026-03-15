package blackjack.util;

import blackjack.common.error.ErrorCode;
import java.util.HashSet;
import java.util.List;

public final class InputValidator {

    public static void validatePlayerNames(final List<String> names) {
        validateUniqueNames(names);
    }

    private static void validateUniqueNames(final List<String> names) {
        if (new HashSet<>(names).size() < names.size()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATED_NAME.getMessage());
        }
    }
}
