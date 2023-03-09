package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class GameResult {

    private final DealerResult dealerResult;
    private final List<PlayerResult> playersResult;

    public GameResult() {
        dealerResult = new DealerResult();
        playersResult = new ArrayList<>();
    }

    public void addResult(Player player, WinResult playerWinResult) {
        dealerResult.add(playerWinResult.counter());
        playersResult.add(new PlayerResult(player.getName(), playerWinResult));
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }

    public List<PlayerResult> getPlayersResult() {
        return playersResult;
    }
}
