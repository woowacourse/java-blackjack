package blackjack.domain;

import java.util.Map;

public class GameResult {

    private final Map<Player, ResultState> result;

    public GameResult(Map<Player, ResultState> result) {
        this.result = result;
    }

    public long getDealerWinCount() {
        return result.values()
                     .stream()
                     .filter(ResultState::isLose)
                     .count();
    }

    public long getDealerLoseCount() {
        return result.values()
                     .stream()
                     .filter(ResultState::isWin)
                     .count();
    }

    public long getDealerDrawCount() {
        return result.values()
                     .stream()
                     .filter(ResultState::isDraw)
                     .count();
    }

    public String getResultStateByPlayer(Player player) {
        return result.get(player)
                     .getValue();
    }
}
