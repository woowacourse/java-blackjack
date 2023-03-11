package domain.participant;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Names {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 4;

    private final List<Name> names;

    private Names(List<Name> names) {
        validate(names);
        this.names = names;
    }

    public static Names from(List<String> names) {
        List<Name> rawNames = names.stream()
                .map(Name::new)
                .collect(Collectors.toList());
        return new Names(rawNames);
    }

    private void validate(final List<Name> names) {
        if (names.size() < MIN_SIZE || MAX_SIZE < names.size()) {
            throw new IllegalArgumentException(String.format("이름을 최소 %s명, 최대 %s명 입력해주세요.", MIN_SIZE, MAX_SIZE));
        }

        if (hasDuplicateName(names)) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }

    private boolean hasDuplicateName(List<Name> names) {
        return names.stream()
                .distinct()
                .count() != names.size();
    }

    public Stream<Name> stream() {
        return names.stream();
    }


    public List<Name> getNames() {
        return names;
    }
}
