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

    public void addResult(Player player, int playerBenefit) {
        dealerResult.add(-playerBenefit);
        playersResult.add(new PlayerResult(player.getName(), playerBenefit));
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }

    public List<PlayerResult> getPlayersResult() {
        return playersResult;
    }
}
