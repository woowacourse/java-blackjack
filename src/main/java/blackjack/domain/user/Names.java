package blackjack.domain.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Name> names = new ArrayList<>();
        for (String value : input.split(DELIMITER)) {
            value = value.trim();
            names.add(new Name(value));
        }
        validateDuplicate(names);
        return names;
    }

    private void validateDuplicate(List<Name> names) {
        Set<Name> nameSet = new HashSet<>(names);
        if (names.size() != nameSet.size()) {
            throw new IllegalArgumentException(NAME_DUPLICATE_ERROR);
        }
    }
}
