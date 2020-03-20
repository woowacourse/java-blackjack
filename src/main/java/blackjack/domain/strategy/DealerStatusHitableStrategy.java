package blackjack.domain.strategy;

import blackjack.domain.*;

public class DealerStatusHitableStrategy implements DealerStatusStrategy {
    @Override
    public PlayerResult calculateResultByPlayerStatus(Dealer dealer, Player player) {
        if (player.isBlackJack()) {
            return PlayerResult.BLACKJACK_WIN;
        }
        if (player.isBust()) {
            return PlayerResult.LOSE;
        }
        return compareScore(dealer.getCards(), player.getCards());
    }

    private PlayerResult compareScore(Cards dealerCards, Cards playerCards) {
        if (dealerCards.calculateScore() < playerCards.calculateScore()) {
            return PlayerResult.WIN;
        }
        if (dealerCards.calculateScore() == playerCards.calculateScore()) {
            return PlayerResult.DRAW;
        }
        return PlayerResult.LOSE;
    }
}