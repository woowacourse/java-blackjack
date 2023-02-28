package blackjack.domain;

import java.util.HashSet;
import java.util.List;

public class Players {

    static final String DUPLICATE_NAMES_MESSAGE = "플레이어의 이름이 중복될 수 없습니다. 입력값:";
    private static final int NAME_COUNT_LOWER_BOUND = 1;
    private static final int NAME_COUNT_UPPER_BOUND = 6;
    static final String INVALID_NAME_COUNT =
            "플레이어는 최소 " + NAME_COUNT_LOWER_BOUND + "명 이상, 최대 " + NAME_COUNT_UPPER_BOUND + "명 이하여야 합니다. 입력값:";

    public static void from(final List<String> names) {
        validateDuplicate(names);
        validateNameCount(names);
    }

    private static void validateDuplicate(final List<String> names) {
        if (isNameDuplicate(names)) {
            throw new IllegalArgumentException(DUPLICATE_NAMES_MESSAGE + names);
        }
    }

    private static boolean isNameDuplicate(final List<String> names) {
        return names.size() != new HashSet<>(names).size();
    }

    private static void validateNameCount(final List<String> names) {
        if (isValidNameCount(names)) {
            throw new IllegalArgumentException(INVALID_NAME_COUNT + names.size());
        }
    }

    private static boolean isValidNameCount(final List<String> names) {
        return names.size() < NAME_COUNT_LOWER_BOUND || NAME_COUNT_UPPER_BOUND < names.size();
    }
}
