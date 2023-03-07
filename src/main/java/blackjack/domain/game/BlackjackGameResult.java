package blackjack.domain.game;

import blackjack.domain.player.Player;
import blackjack.domain.player.Result;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackGameResult {
    private final Map<Player, Result> result;

    public BlackjackGameResult(final Map<Player, Result> result) {
        this.result = result;
    }

    public int getDealerWinCount() {
        return Collections.frequency(result.values(), Result.LOSE);
    }

    public int getDealerPushCount() {
        return Collections.frequency(result.values(), Result.PUSH);
    }

    public int getDealerLoseCount() {
        return Collections.frequency(result.values(), Result.WIN);
    }

    public Map<Player, Result> getResult() {
        return new LinkedHashMap<>(result);
    }
}
