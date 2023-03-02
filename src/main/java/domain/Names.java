package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Names {

    private static final int MIN_SIZE_EXCLUSIVE = 1;
    private static final int MAX_SIZE_EXCLUSIVE = 7;
    private final List<Name> names;

    public Names(final List<String> names) {
        validateSize(names);
        this.names = createNames(names);
    }

    private static void validateSize(final List<String> names) {
        if (names.size() < MIN_SIZE_EXCLUSIVE || names.size() > MAX_SIZE_EXCLUSIVE) {
            throw new IllegalArgumentException("전체 플레이어 수는 1명 이상 7명 이하여야 합니다!");
        }
    }

    private List<Name> createNames(final List<String> names) {
        return names.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public List<Name> getNames() {
        return Collections.unmodifiableList(names);
    }
}
