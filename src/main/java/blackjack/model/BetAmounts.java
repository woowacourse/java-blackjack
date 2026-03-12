package blackjack.model;

import blackjack.model.user.Player;
import java.util.Map;

public class BetAmounts {

    private final Map<Player, BetAmount> betAmounts;

    public BetAmounts(Map<Player, BetAmount> betAmounts) {
        this.betAmounts = betAmounts;
    }


}
