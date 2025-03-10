package blackjack.domain.fixture;

import blackjack.domain.gambler.Name;
import java.util.Arrays;
import java.util.List;

public class NameFixture {
    public static List<Name> createNames(String... names) {
        return Arrays.stream(names)
                .map(Name::new)
                .toList();
    }
}
