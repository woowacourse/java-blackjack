package blackjack.domain;

import blackjack.util.NullChecker;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Names {

    private static final String DELIMITER = ",";

    private List<Name> names;

    private Names(List<Name> names) {
        NullChecker.validateNotNull(names);
        this.names = names;
    }

    public static Names of(String names) {
        NullChecker.validateNotNull(names);
        return new Names(Arrays.stream(names.split(DELIMITER))
            .map(Name::new)
            .collect(Collectors.toList()));
    }

    public List<Name> getNames() {
        return names;
    }
}
