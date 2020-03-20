package blackjack.domain.strategy;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.PlayerResult;

public class DealerBlackJackStrategy implements DealerStatusStrategy {
    @Override
    public PlayerResult calculateResultByPlayerStatus(Dealer dealer, Player player) {
        if (player.isBlackJack()) {
            return PlayerResult.DRAW;
        }
        return PlayerResult.LOSE;
    }
}
