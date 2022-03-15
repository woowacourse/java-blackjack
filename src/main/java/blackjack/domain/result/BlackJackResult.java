package blackjack.domain.result;

import blackjack.domain.player.Gamer;
import java.util.Map;

public class BlackJackResult {

    public final Map<Gamer, ResultStrategy> result;

    public BlackJackResult(final Map<Gamer, ResultStrategy> result) {
        this.result = result;
    }

    public Map<Gamer, ResultStrategy> getResult() {
        return result;
    }
}
