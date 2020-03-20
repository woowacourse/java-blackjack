package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Names {

    private static final String DELIMITER = ",";
    private static final String NULL_EXCEPTION_MESSAGE = "인자로 Null이 들어왔습니다.";

    private List<Name> names;

    private Names(List<Name> names) {
        validateNotNull(names);
        this.names = names;
    }

    public static Names of(String names) {
        validateNotNull(names);
        return new Names(Arrays.stream(names.split(DELIMITER))
            .map(Name::new)
            .collect(Collectors.toList()));
    }

    private static <T> void validateNotNull(T names) {
        if (Objects.isNull(names)) {
            throw new IllegalArgumentException(NULL_EXCEPTION_MESSAGE);
        }
    }

    public List<Name> getNames() {
        return names;
    }
}
