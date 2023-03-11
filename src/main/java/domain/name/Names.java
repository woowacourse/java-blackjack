package domain.name;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Names {
    private final List<Name> names;

    private Names(final List<Name> names) {
        this.names = names;
    }

    public static Names of(final List<String> nameValues) {
        validate(nameValues);
        List<Name> names = nameValues.stream().map(name -> new Name(name)).collect(Collectors.toList());
        return new Names(names);
    }

    private static void validate(final List<String> names) {
        validateDuplication(names);
    }

    private static void validateDuplication(final List<String> names) {
        Set<String> distinctNames = new HashSet<>(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public int size() {
        return names.size();
    }

    public Name get(int index) {
        return names.get(index);
    }

    public List<Name> getNames() {
        return Collections.unmodifiableList(names);
    }
}
