package blackjack.domain.participant;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Names {

    public static final int MAX_PLAYER = 7;

    private final List<Name> names;

    private Names(List<Name> names) {
        this.names = names;
        validateDuplicate(names);
        validatePlayerCount();
    }

    public static Names of(List<String> inputNames) {
        List<Name> names = inputNames.stream()
                .map(Name::new)
                .collect(Collectors.toList());
        return new Names(names);
    }

    private void validateDuplicate(final List<Name> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    private void validatePlayerCount() {
        if (names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("최대 참여 플레이어는 " + MAX_PLAYER + "명입니다.");
        }
    }

    public Name get(int index) {
        return names.get(index);
    }

    public int size() {
        return names.size();
    }

    public Stream<Name> stream() {
        return names.stream();
    }

    public List<Name> toList() {
        return Collections.unmodifiableList(names);
    }
}
