package blackjack.model.results;

import blackjack.model.participants.Player;
import blackjack.vo.Money;
import java.util.Map;

public class PlayerProfit {
    private final Map<Player, Money> result;

    public PlayerProfit(Map<Player, Money> result) {
        this.result = result;
    }

    public Map<Player, Money> getResult() {
        return result;
    }
}
