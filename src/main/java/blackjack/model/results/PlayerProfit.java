package blackjack.model.results;

import blackjack.model.participants.Player;
import java.util.Map;

public class PlayerProfit {
    private final Map<Player, Integer> result;

    public PlayerProfit(Map<Player, Integer> result) {
        this.result = result;
    }

    public Map<Player, Integer> getResult() {
        return result;
    }
}
