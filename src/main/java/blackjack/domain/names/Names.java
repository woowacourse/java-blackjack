package blackjack.domain.names;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Names {

    private static final String DELIMITER = ",";

    private final List<Name> elements;

    private Names(List<Name> elements) {
        this.elements = new ArrayList<>(elements);
    }

    public static Names valueOf(String unparsedNames) {
        List<Name> names = Arrays.stream(unparsedNames.split(DELIMITER, -1))
            .map(Name::new)
            .collect(Collectors.toList());
        validateDuplication(names);
        return new Names(names);
    }

    private static void validateDuplication(List<Name> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public List<Name> unwrap() {
        return new ArrayList<>(elements);
    }
}
