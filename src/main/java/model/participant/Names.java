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
        validateDuplicated(names);
        validateAllowedName(names);
    }

    private void validateAllowedName(List<String> inputNames){
        for (String inputName : inputNames) {
            isContainsDealerName(inputName);
        }
    }

    private void isContainsDealerName(String inputName) {
        if (inputName.contains("딜러")){
            throw new IllegalArgumentException("'딜러'가 포함된 사용자명은 사용할 수 없습니다.");
        }
    }

    private void validateDuplicated(List<String> names) {
        Set<String> duplicateSize = new HashSet<>(names);
        if (duplicateSize.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 포함되어 있습니다.");
        }
    }

    public List<Name> getPlayerNames() {
        return Collections.unmodifiableList(playerNames);
    }
}
