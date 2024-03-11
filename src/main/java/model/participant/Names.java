package model.participant;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Names {
    private final List<Name> playerNames;

    public Names(List<String> names) {
        validate(names);
        this.playerNames = fromNameStrings(names);
    }

    private List<Name> fromNameStrings(List<String> names) {
        return names.stream()
                .map(Name::new)
                .toList();
    }

    private void validate(List<String> names) {
        validateEmptyNames(names);
        validateDuplicated(names);
    }

    private void validateDuplicated(List<String> names) {
        Set<String> duplicateSize = new HashSet<>(names);
        if (duplicateSize.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 포함되어 있습니다.");
        }
    }

    private void validateEmptyNames(List <String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("참가자는 적어도 1명 이상이어야 합니다.");
        }
    }

    public List<Name> getPlayerNames() {
        return Collections.unmodifiableList(playerNames);
    }
}
