package blackjack.domain.participants;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Names {

    private static final int MIN_PEOPLE = 1;
    private static final int MAX_PEOPLE = 7;
    private final List<Name> values;

    public Names(final List<Name> values) {
        validateSameName(values);
        validatePeopleNumber(values);
        this.values = values;
    }

    public Names(final String... args) {
        this(Arrays.stream(args)
            .map(Name::new)
            .collect(Collectors.toList()));
    }

    private void validateSameName(final List<Name> values) {
        final Set<Name> names = new HashSet<>(values);
        if (names.size() != values.size()) {
            throw new IllegalArgumentException("동일한 이름을 입력하면 안 됩니다.");
        }
    }

    private void validatePeopleNumber(final List<Name> values) {
        if (values.size() < MIN_PEOPLE || values.size() > MAX_PEOPLE) {
            throw new IllegalArgumentException("딜러를 제외한 플레이어는 1명 이상 7명이하이어야 합니다.");
        }
    }

    public List<Name> toList() {
        return Collections.unmodifiableList(values);
    }
}
