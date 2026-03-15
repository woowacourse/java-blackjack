package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Names {
    private final List<Name> names;

    public Names(List<Name> names) {
        validateDuplicateNames(names);
        this.names = names;
    }

    private void validateDuplicateNames(List<Name> names) {
        Set<Name> uniqueNames = new HashSet<>(names);
        if (uniqueNames.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public List<Name> getNames() {
        return List.copyOf(names);
    }
}
