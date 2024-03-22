package blackjack.domain.gamer;

import java.util.HashSet;
import java.util.List;

public class Names {
    private static final String NAMES_DUPLICATE_ERROR = "플레이어 이름은 중복될 수 없습니다.";

    private final List<Name> names;

    public Names(List<String> names) {
        validateDuplicate(names);
        this.names = names.stream()
                .map(Name::new)
                .toList();
    }

    private static void validateDuplicate(List<String> names) {
        int distinctCount = new HashSet<>(names).size();
        if (names.size() != distinctCount) {
            throw new IllegalArgumentException(NAMES_DUPLICATE_ERROR);
        }
    }

    public List<Name> names() {
        return names;
    }
}
