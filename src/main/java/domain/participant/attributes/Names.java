package domain.participant.attributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Names {

    private final List<Name> names;

    public Names(final List<String> names) {
        validateNotDuplicated(names);
        this.names = names.stream()
                .map(Name::new)
                .toList();
    }

    private void validateNotDuplicated(final List<String> names) {
        Set<String> distinctNames = new HashSet<>();
        names.forEach(name -> {
            if (distinctNames.contains(name)) {
                throw new IllegalArgumentException("중복된 이름을 입력할 수 없습니다: %s".formatted(name));
            }
            distinctNames.add(name);
        });
    }

    public List<Name> toList() {
        return List.copyOf(names);
    }
}
