package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Names {

    private final List<Name> names;

    public Names(String names) {
        this.names = splitNames(names);
    }

    private List<Name> splitNames(String names) {
        return Arrays.stream(names.split(","))
                .map(Name::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
