package blackjack.domain.result;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.bet.Money;
import blackjack.domain.participant.Player;

public class GameResult {

    private final DealerResult dealerResult;
    private final List<PlayerResult> playersResult;

    public GameResult() {
        dealerResult = new DealerResult();
        playersResult = new ArrayList<>();
    }

    public void addResult(Player player, Money betMoney) {
        dealerResult.add(betMoney.getNegativeMoney());
        playersResult.add(new PlayerResult(player.getName(), betMoney));
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }

    public List<PlayerResult> getPlayersResult() {
        return playersResult;
    }
}
