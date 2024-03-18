package blackjack.domain.result;

import blackjack.domain.player.Profit;
import blackjack.domain.player.Name;
import java.util.Map;

public class Result {
    private final Map<Name, Profit> result;

    public Result(Map<Name, Profit> result) {
        this.result = result;
    }

    public Map<Name, Profit> getResult() {
        return result;
    }
}
