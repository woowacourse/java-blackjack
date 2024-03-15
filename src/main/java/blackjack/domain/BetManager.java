package blackjack.domain;

import blackjack.domain.participant.BetMoney;
import blackjack.domain.participant.Player;
import java.util.Map;

public class BetManager {

    private final Map<Player, BetMoney> betMoneys;

    public BetManager(Map<Player, BetMoney> betMoneys) {
        this.betMoneys = betMoneys;
    }

    public BetMoney findPlayerBetMoney(Player player) {
        return betMoneys.get(player);
    }
}
