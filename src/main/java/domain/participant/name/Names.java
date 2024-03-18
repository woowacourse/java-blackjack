package domain.participant.name;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class Names {
    private static final int MIN_NAME_COUNT = 1;
    private static final int MAX_NAME_COUNT = 6;

    private final List<Name> names;

    public Names(List<String> rawNames) {
        validateNameCount(rawNames);
        validateDuplicatedName(rawNames);
        this.names = rawNames.stream()
                .map(Name::new)
                .toList();
    }

    private void validateNameCount(List<String> rawNames) {
        int nameCount = rawNames.size();
        if (nameCount < MIN_NAME_COUNT || MAX_NAME_COUNT < nameCount) {
            throw new IllegalArgumentException(
                    String.format("이름 개수는 %d ~ %d개여야 합니다.", MIN_NAME_COUNT, MAX_NAME_COUNT)
            );
        }
    }

    private void validateDuplicatedName(List<String> rawNames) {
        Map<String, Long> nameCounts = getPlayerNameCounts(rawNames);
        Optional<String> duplicatedName = findDuplicatedName(nameCounts);
        if (duplicatedName.isPresent()) {
            throw new IllegalArgumentException(
                    String.format("rejected value: %s - 중복된 이름이 존재합니다.", duplicatedName.get())
            );
        }
    }

    private Map<String, Long> getPlayerNameCounts(List<String> rawNames) {
        return rawNames.stream()
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));
    }

    private Optional<String> findDuplicatedName(Map<String, Long> nameCounts) {
        return nameCounts.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Entry::getKey)
                .findFirst();
    }

    public List<Name> getNames() {
        return Collections.unmodifiableList(names);
    }
}
