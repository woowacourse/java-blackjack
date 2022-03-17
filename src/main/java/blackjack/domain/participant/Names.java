package blackjack.domain.participant;

import java.util.List;
import java.util.stream.Collectors;

public class Names {

    private final List<Name> names;

    public Names(List<String> nameStrings) {
        nameStrings = List.copyOf(nameStrings);
        validate(nameStrings);
        names = createNames(nameStrings);
    }

    private void validate(List<String> nameStrings) {
        if (nameStrings.stream().distinct().count() != nameStrings.size()) {
            throw new IllegalArgumentException("[ERROR]: 플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private List<Name> createNames(List<String> nameStrings) {
        return nameStrings.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    public List<Name> getNames() {
        return names;
    }
}
