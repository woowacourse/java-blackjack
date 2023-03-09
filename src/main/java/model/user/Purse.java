package model.user;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class Purse {

    private final Map<Player, Money> purses;

    private Purse(final Map<Player, Money> purses) {
        this.purses = purses;
    }

    public static Purse create(final List<Player> players) {
        final Map<Player, Money> purses = players.stream()
                .collect(toMap(Function.identity(), player -> Money.zero()));
        return new Purse(purses);
    }
}
