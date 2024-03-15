package blackjack.domain.result;

import blackjack.domain.participant.BetAmount;
import blackjack.domain.participant.Player;
import java.util.Map;

public class Pot {
    private final Map<Player, BetAmount> pot;

    public Pot(Map<Player, BetAmount> pot) {
        this.pot = pot;
    }

    public Map<Player, BetAmount> getPot() {
        return pot;
    }
}
