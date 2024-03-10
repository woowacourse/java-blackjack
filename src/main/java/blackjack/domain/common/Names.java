package blackjack.domain.common;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Names {
    private static final Integer MAX_SIZE = 7;

    private final List<Name> value;

    private Names(final List<Name> value) {
        this.value = value;
    }

    public static Names from(final List<String> names) {
        validate(names);
        return new Names(names.stream()
                              .map(Name::new)
                              .toList());
    }

    private static void validate(final List<String> names) {
        validateSize(names);
        validateDuplicate(names);
    }

    private static void validateDuplicate(final List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
        }
    }

    private static void validateSize(final List<String> names) {
        if (names.size() > MAX_SIZE) {
            throw new IllegalArgumentException(String.format("%d명 까지만 가능합니다.", MAX_SIZE));
        }
    }

    public Stream<Name> stream() {
        return value.stream();
    }
}
