package blackjack.domain.bet;

import blackjack.domain.game.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Profit {

    private final Map<Player, Betting> bettings = new HashMap<>();

    public void bet(final Player player, final Supplier<Betting> supplier) {
        bettings.put(player, getBetting(supplier));
    }

    private Betting getBetting(final Supplier<Betting> supplier) {
        return supplier.get();
    }
}
