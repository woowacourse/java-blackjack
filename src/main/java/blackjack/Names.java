package blackjack;

import java.util.List;

public class Names {
    private List<Name> value;

    private Names(List<Name> value) {
        this.value = value;
    }

    public static Names from(List<String> names) {
        return new Names(names.stream().map(Name::new).toList());
    }
}
