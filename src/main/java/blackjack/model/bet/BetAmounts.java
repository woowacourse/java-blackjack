package blackjack.model.bet;

import blackjack.model.user.Player;
import java.util.Map;

public class BetAmounts {

    private final Map<Player, BetAmount> betAmounts;

    public BetAmounts(Map<Player, BetAmount> betAmounts) {
        this.betAmounts = Map.copyOf(betAmounts);
    }

    public BetAmount findByPlayer(Player player) {
        return betAmounts.get(player);
    }
}
