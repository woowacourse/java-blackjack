package blackjack.domain.strategy;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.PlayerResult;

public class DealerBustStrategy implements DealerStatusStrategy {
    @Override
    public PlayerResult calculateResultByPlayerStatus(Dealer dealer, Player player) {
        if (player.isBust()) {
            return PlayerResult.LOSE;
        }
        if (player.isBlackJack()) {
            return PlayerResult.BLACKJACK_WIN;
        }
        return PlayerResult.WIN;
    }
}
