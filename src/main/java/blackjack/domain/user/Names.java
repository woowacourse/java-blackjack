package blackjack.domain.user;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Names {

    private static final String DELIMITER = ",";
    private static final String NAME_DUPLICATE_ERROR = "[ERROR] 중복되는 이름을 입력할 수 없습니다.";

    private final List<Name> names;

    public Names(String input) {
        this.names = makeNames(input);
    }

    public List<Name> getNames() {
        return names;
    }

    private List<Name> makeNames(String input) {
        List<Name> names = Arrays.stream(input.split(DELIMITER))
            .map(Name::new)
            .collect(Collectors.toList());
        validateDuplicate(names);
        return Collections.unmodifiableList(names);
    }

    private void validateDuplicate(List<Name> names) {
        Set<Name> nameSet = new HashSet<>(names);
        if (names.size() != nameSet.size()) {
            throw new IllegalArgumentException(NAME_DUPLICATE_ERROR);
        }
    }
}
