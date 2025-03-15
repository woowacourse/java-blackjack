package model.participants;

import java.util.HashSet;
import java.util.List;

public class Names {
    private static final String WARNING_DUPLICATE_NAMES = "[ERROR] 이름은 중복될 수 없습니다.";

    private final List<Name> names;

    public Names(List<String> names) {
        validateNames(names);
        this.names = createNames(names);
    }

    private void validateNames(List<String> names) {
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException(WARNING_DUPLICATE_NAMES);
        }
    }

    private List<Name> createNames(List<String> names) {
        return names.stream()
                .map(Name::new)
                .toList();
    }

    public List<Name> getNames() {
        return names;
    }
}
