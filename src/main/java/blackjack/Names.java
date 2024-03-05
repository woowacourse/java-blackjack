package blackjack;

import java.util.List;
import java.util.Set;

public class Names {
    private List<Name> value;

    private Names(List<Name> value) {
        this.value = value;
    }

    public static Names from(List<String> names) {
        validateDuplicate(names);
        return new Names(names.stream().map(Name::new).toList());
    }

    private static void validateDuplicate(List<String> names) {
        if (Set.of(names).size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
        }
    }
}
