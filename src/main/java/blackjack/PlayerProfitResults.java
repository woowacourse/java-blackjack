package blackjack;

import blackjack.domain.bet.Profit;
import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.Map;

public class PlayerProfitResults {
    private final Map<Player, Profit> results;

    public PlayerProfitResults(Map<Player, Profit> results) {
        this.results = results;
    }

    public Map<Player, Profit> get() {
        return Collections.unmodifiableMap(results);
    }
}
