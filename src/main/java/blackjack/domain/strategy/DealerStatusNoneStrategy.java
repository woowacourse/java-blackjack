package blackjack.domain.strategy;

import blackjack.domain.*;

public class DealerStatusNoneStrategy implements DealerStatusStrategy {
    @Override
    public PlayerResult calculateResultByPlayerStatus(Dealer dealer, Player player) {
        if (player.getStatus() == Status.BLACKJACK) {
            return PlayerResult.WIN;
        }
        if (player.getStatus() == Status.BUST) {
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