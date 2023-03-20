package blackjack.domain.game;

import java.util.Collections;
import java.util.Map;

import blackjack.domain.participant.Player;

public class FinalProfit {

    private final Map<Player, Money> playerMoney;

    public FinalProfit(final Map<Player, Money> playerMoney) {
        this.playerMoney = playerMoney;
    }

    public int calculateDealerProfit() {
         return playerMoney.values()
                .stream()
                .mapToInt(Money::getValue)
                .sum() * -1;
    }

    public Map<Player, Money> getPlayerMoney() {
        return Collections.unmodifiableMap(playerMoney);
    }
}
