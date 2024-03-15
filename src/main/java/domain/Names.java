package domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Names {
    private final List<Name> names;

    public Names(final List<String> names) {
        this.names = createNames(names);
        validate(names);
    }

    private static List<Name> createNames(final List<String> names) {
        return names.stream()
                .map(Name::new)
                .toList();
    }

    private void validate(final List<String> names) {
        if (hasDuplicatePlayers(names)) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다");
        }
    }

    private boolean hasDuplicatePlayers(final List<String> names) {
        return Set.copyOf(names).size() != names.size();
    }

    public List<Name> getNames() {
        return Collections.unmodifiableList(names);
    }

    public int size() {
        return names.size();
    }

    public Name get(final int i) {
        if (i >= size()) {
            throw new IllegalArgumentException("이름의 숫자보다 많은 플레이어는 만들 수 없습니다.");
        }
        return names.get(i);
    }
}
