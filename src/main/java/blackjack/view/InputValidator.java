package blackjack.view;

import java.util.HashSet;
import java.util.List;

public final class InputValidator {

    public static void validatePlayerNames(final List<String> names) {
        if (new HashSet<>(names).size() < names.size()) {
            throw new IllegalArgumentException("중복된 플레이어 이름이 존재합니다.");
        }
    }
}
