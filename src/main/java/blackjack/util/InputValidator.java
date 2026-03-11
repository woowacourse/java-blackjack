package blackjack.util;

import java.util.HashSet;
import java.util.List;

public final class InputValidator {

    private static final String BLANK = " ";

    public static void validatePlayerNames(final List<String> names) {
        if (names == null) {
            throw new IllegalArgumentException("names가 null입니다.");
        }

        boolean hasInvalidName = names.stream()
                .anyMatch(name -> name.startsWith(BLANK) || name.endsWith(BLANK));

        if (hasInvalidName) {
            throw new IllegalArgumentException("이름이 공백으로 시작하거나 끝납니다.");
        }

        if (new HashSet<>(names).size() < names.size()) {
            throw new IllegalArgumentException("중복된 플레이어 이름이 존재합니다.");
        }
    }

    public static void validateBetAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("자연수가 아닙니다.");
        }

        if (amount % 100 != 0) {
            throw new IllegalArgumentException("100원 단위가 아닙니다.");
        }
    }
}
