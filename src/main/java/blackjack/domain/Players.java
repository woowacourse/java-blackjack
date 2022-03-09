package blackjack.domain;

import java.util.List;

public class Players {

    public Players(final List<String> names) {
        validateDuplication(names);
    }

    private void validateDuplication(final List<String> names) {
        final int distinctCount = (int) names.stream()
                .distinct()
                .count();
        if (distinctCount != names.size()) {
            throw new IllegalArgumentException("이름 간에 중복이 있으면 안됩니다.");
        }
    }
}
