package blackjack.domain.strategy;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Status;
import blackjack.domain.PlayerResult;

public class DealerNoneStrategy implements DealerStatusStrategy {
    @Override
    public PlayerResult calculateResultByPlayerStatus(Dealer dealer, Player player) {
        if (player.getStatus() == Status.BLACKJACK) {
            return PlayerResult.WIN;
        }
        if (player.getStatus() == Status.BUST) {
            return PlayerResult.LOSE;
        }
        return compareScore(dealer, player);
    }

    private PlayerResult compareScore(Dealer dealer, Player player) {
        if (dealer.calculateScore() < player.calculateScore()) {
            return PlayerResult.WIN;
        }
        if (dealer.calculateScore() == player.calculateScore()) {
            return PlayerResult.DRAW;
        }
        return PlayerResult.LOSE;
    }
}