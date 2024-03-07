package blackjack.domain.common;

import java.util.HashSet;
import java.util.List;

public class Names {
    private static final Integer MAX_SIZE = 7;
    private List<Name> value;

    private Names(List<Name> value) {
        this.value = value;
    }

    public static Names from(List<String> names) {
        validate(names);
        return new Names(names.stream().map(Name::new).toList());
    }
    private static void validate(List<String> names){
        validateSize(names);
        validateDuplicate(names);
    }
    private static void validateDuplicate(List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
        }
    }
    private static void validateSize(List<String> names){
        if(names.size()>MAX_SIZE){
            throw new IllegalArgumentException(String.format("%d명 까지만 가능합니다.",MAX_SIZE));
        }
    }
}
