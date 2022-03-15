package blackjack.domain.result;

import blackjack.domain.player.Player;
import java.util.Map;

public class BlackJackResult {

    public final Map<Player, ResultStrategy> result;

    public BlackJackResult(final Map<Player, ResultStrategy> result) {
        this.result = result;
    }

    public Map<Player, ResultStrategy> getResult() {
        return result;
    }
}
