package blackjack.util;

import java.util.Objects;

public class NullChecker {

    private static final String NULL_USE_EXCEPTION_MESSAGE = "잘못된 인자 - Null 사용";

    public static void validateNotNull(Object... objects) {
        for(Object object : objects) {
            if (Objects.isNull(object)) {
                throw new IllegalArgumentException(NULL_USE_EXCEPTION_MESSAGE);
            }
        }
    }
}
