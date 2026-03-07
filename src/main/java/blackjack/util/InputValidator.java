package blackjack.util;

import java.util.HashSet;
import java.util.List;

public final class InputValidator {

    public static void validatePlayerNames(final List<String> names) {
        boolean hasInvalidName = names.stream()
                .anyMatch(name -> name.startsWith(" ") || name.endsWith(" "));

        if (hasInvalidName) {
            throw new IllegalArgumentException("이름이 공백으로 시작하거나 끝납니다.");
        }

        if (new HashSet<>(names).size() < names.size()) {
            throw new IllegalArgumentException("중복된 플레이어 이름이 존재합니다.");
        }
    }
}
