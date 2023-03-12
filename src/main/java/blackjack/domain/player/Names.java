package blackjack.domain.player;

import java.util.List;
import java.util.stream.Collectors;

public class Names {

    private static final int MINIMUM_NAMES_RANGE = 1;
    private static final int MAXIMUM_NAMES_RANGE = 20;

    private final List<Name> names;

    public Names(List<String> names) {
        validateDuplicateNames(names);
        validateRange(names);
        this.names = generateNames(names);
    }

    private List<Name> generateNames(List<String> names) {
        return names.stream()
                .map(Name::new )
                .collect(Collectors.toList());
    }

    private void validateDuplicateNames(List<String> names) {
        if (getDistinctNames(names) != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 입력할 수 없습니다.");
        }
    }

    private int getDistinctNames(List<String> splitNames) {
        return (int) splitNames.stream()
                .distinct()
                .count();
    }

    private void validateRange(List<String> names) {
        if (names.size() < MINIMUM_NAMES_RANGE || names.size() > MAXIMUM_NAMES_RANGE) {
            throw new IllegalArgumentException(
                    String.format("플레이어 이름의 수가 %d개 이상 %d개 이하여야 합니다.", MINIMUM_NAMES_RANGE, MAXIMUM_NAMES_RANGE)
            );
        }
    }

    public List<Name> getNames() {
        return names;
    }

}
