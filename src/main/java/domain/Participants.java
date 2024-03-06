package domain;

import java.util.List;
import java.util.Set;

public class Participants {
    private final List<Participant> value;
    public Participants(List<String> names) {
        validate(names);
        value = names.stream().map(name -> new Participant(new Name(name)))
                .toList();
    }

    public List<Participant> getValue() {
        return value;
    }

    public List<Name> getNames() {
        return value.stream()
                .map(Participant::getName)
                .toList();
    }

    private static void validate(List<String> names) {
        if (names.size() < 2 || names.size() > 8) {
            throw new IllegalArgumentException("최소 2명 최대 8명까지 입력받을 수 있습니다.");
        }
        Set<String> distinctNames = Set.copyOf(names);

        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }
}
