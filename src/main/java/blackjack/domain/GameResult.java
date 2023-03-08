package blackjack.domain;

import java.util.Map;

public class GameResult {

    private final Map<Player, ResultState> result;

    public GameResult(Map<Player, ResultState> result) {
        this.result = result;
    }

    public long getDealerWin() {
        return result.size() - getPlayerWinCount();
    }

    public long getDealerLose() {
        return getPlayerWinCount();
    }

    private long getPlayerWinCount() {
        return result.values()
                     .stream()
                     .filter(ResultState::isWin)
                     .count();
    }

    public String getResultStateByPlayer(Player player) {
        return result.get(player)
                     .getValue();
    }
}
