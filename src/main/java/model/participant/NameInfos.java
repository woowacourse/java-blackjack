package model.participant;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NameInfos {
    private final List<Name> playerNames;
    private final Map<Name, Integer> bettingAmounts = new HashMap<>();

    public NameInfos(List<String> names) {
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
    }

    private void validateDuplicated(List<String> names) {
        Set<String> duplicateSize = new HashSet<>(names);
        if (duplicateSize.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 포함되어 있습니다.");
        }
    }

    public void initPlayerBettingAmount(Name name, int bettingAmount){
        bettingAmounts.put(name,bettingAmount);
    }

    public int getBettingAmount(Name name) {
        return bettingAmounts.getOrDefault(name, 0);
    }

    public List<Name> getPlayerNames() {
        return Collections.unmodifiableList(playerNames);
    }
}
