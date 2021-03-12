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
        List<Name> names = parseNames(unparsedNames);
        validateDuplication(names);
        return new Names(names);
    }

    private static List<Name> parseNames(String unparsedNames) {
        return Arrays.stream(unparsedNames.split(DELIMITER, -1))
            .map(Name::new)
            .collect(Collectors.toList());
    }

    private static void validateDuplication(List<Name> names) {
        long removedDuplicationCount = countDistinctName(names);
        if (names.size() != removedDuplicationCount) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    private static long countDistinctName(List<Name> names) {
        return names.stream()
            .distinct()
            .count();
    }

    public List<Name> unwrap() {
        return new ArrayList<>(elements);
    }
}
