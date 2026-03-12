package blackjack.domain.participant;

import blackjack.domain.betting.BettingMoney;
import java.util.List;
import java.util.stream.IntStream;

public class PlayerFactory {

    private PlayerFactory() {}

    public static Player create(final String name, final int amount) {
        return new Player(new Name(name), new BettingMoney(amount));
    }

    public static Players createPlayers(final List<String> names, final List<Integer> amounts) {
        final List<Player> players = IntStream.range(0, names.size())
                .mapToObj(i -> create(names.get(i), amounts.get(i)))
                .toList();
        return new Players(players);
    }
}
