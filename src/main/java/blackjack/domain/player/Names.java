package blackjack.domain.player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Names {

    private static final int MINIMUM_NAMES_RANGE = 1;
    private static final int MAXIMUM_NAMES_RANGE = 20;

    private final List<Name> names;

    public Names(String names) {
        List<Name> splitNames = splitNames(names);
        validateDuplicateNames(splitNames);
        validateRange(splitNames);
        this.names = splitNames;
    }

    private List<Name> splitNames(String names) {
        return Arrays.stream(names.split(","))
                .map(Name::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Name> getNames() {
        return names;
    }

    private void validateDuplicateNames(List<Name> names) {
        if (getDistinctNames(names) != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 입력할 수 없습니다.");
        }
    }

    private int getDistinctNames(List<Name> splitNames) {
        return (int) splitNames.stream()
                .distinct()
                .count();
    }

    private void validateRange(List<Name> names) {
        if (names.size() < MINIMUM_NAMES_RANGE || names.size() > MAXIMUM_NAMES_RANGE) {
            throw new IllegalArgumentException("이름의 수가 1이상 20이하여야 합니다.");
        }
    }

}
