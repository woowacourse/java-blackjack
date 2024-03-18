package domain.participant;

import java.util.List;
import java.util.Set;

public class Names {

    private static final int MIN_SIZE = 2;
    private static final int MAX_SIZE = 8;

    private final List<Name> names;

    private Names(final List<Name> names) {
        this.names = names;
    }

    public static Names from(final List<String> names) {
        validate(names);
        return new Names(names.stream().map(Name::new).toList());
    }

    public List<Name> getNames() {
        return names;
    }

    private static void validate(final List<String> names) {
        validateSize(names);
        validateDuplication(names);
    }

    private static void validateSize(final List<String> names) {
        if (names.size() < MIN_SIZE || MAX_SIZE < names.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 참여자 수입니다.");
        }
    }

    private static void validateDuplication(final List<String> names) {
        if (names.size() != Set.copyOf(names).size()) {
            throw new IllegalArgumentException("[ERROR] 참여자 이름은 중복될 수 없습니다.");
        }
    }
}
