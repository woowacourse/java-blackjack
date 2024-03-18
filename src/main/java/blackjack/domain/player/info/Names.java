package blackjack.domain.player.info;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Names {

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
        validateDuplicate(names);
    }

    private static void validateDuplicate(final List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
        }
    }

    public Stream<Name> stream() {
        return this.value.stream();
    }
}
