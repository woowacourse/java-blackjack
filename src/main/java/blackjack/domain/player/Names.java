package blackjack.domain.player;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Names {

    private static final int MINIMUM_NAMES_RANGE = 1;
    private static final int MAXIMUM_NAMES_RANGE = 20;
    private static final Pattern INPUT_NAMES_PATTERN = Pattern.compile("([a-zA-Z]{1,10})(,[a-zA-Z]{1,10})*");

    private final List<Name> names;

    public Names(String names) {
        validateContinuousComma(names);
        List<Name> splitNames = splitNames(names);
        validateDuplicateNames(splitNames);
        validateRange(splitNames);
        this.names = splitNames;
    }

    private void validateContinuousComma(String names) {
        Matcher matcher = INPUT_NAMES_PATTERN.matcher(names);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("입력된 플레이어들의 이름 형식이 올바르지 않습니다.");
        }
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
            throw new IllegalArgumentException(
                    String.format("플레이어 이름의 수가 %d개 이상 %d개 이하여야 합니다.", MINIMUM_NAMES_RANGE, MAXIMUM_NAMES_RANGE)
            );
        }
    }

}
