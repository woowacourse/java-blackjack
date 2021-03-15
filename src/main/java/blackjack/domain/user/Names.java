package blackjack.domain.user;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Names {
    private final List<Name> names;

    private Names(List<Name> names) {
        this.names = names;
    }

    public static Names of(List<String> name) {
        return name.stream()
                .map(Name::of)
                .collect(collectingAndThen(toList(), Names::new));
    }

    public Stream<Name> stream() {
        return names.stream();
    }
}
