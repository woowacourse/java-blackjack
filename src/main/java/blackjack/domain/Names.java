package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Names {
    private static final String EMPTY_OR_NULL_NAMES_EXCEPTION_MESSAGE = "Empty or null names exception.";
    private static final String DELIMITER = ",";

    private List<Name> names;

    public Names(List<Name> names) {
        validateNames(names);
        this.names = names;
    }

    public Names(String input) {
        this(Arrays.stream(input.split(DELIMITER))
                .map(Name::new)
                .collect(Collectors.toList()));
    }

    private void validateNames(List<Name> names) {
        if (Objects.isNull(names) || names.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_OR_NULL_NAMES_EXCEPTION_MESSAGE);
        }
    }

    public List<Name> getNames() {
        return names;
    }

    public boolean isSizeInRange(int minimumPlayerCount, int maximumPlayerCount) {
        return names.size() >= minimumPlayerCount && names.size() <= maximumPlayerCount;
    }
}
