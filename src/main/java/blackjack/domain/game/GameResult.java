package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.ResultStatus;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, ResultStatus> result;

    public GameResult() {
        this.result = new LinkedHashMap<>();
    }

    public void compare(final Player player, final Dealer dealer) {
        final ResultStatus resultStatus = judgeResultStatus(player, dealer);

        result.put(player, resultStatus);
    }

    private ResultStatus judgeResultStatus(final Player player, final Dealer dealer) {
        return ResultStatus.of(player, dealer);
    }

    public Map<Player, ResultStatus> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
