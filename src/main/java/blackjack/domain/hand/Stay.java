package blackjack.domain.hand;

import blackjack.domain.card.CardBundle;
import blackjack.domain.card.Score;

public final class Stay extends Finished {

    Stay(CardBundle cardBundle) {
        super(cardBundle);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    protected double calculateDuelResult(CardHand dealerHand) {
        if (dealerHand.isBlackjack()) {
            return LOSE_BETTING_YIELD;
        }
        if (dealerHand.isBust()) {
            return WIN_BETTING_YIELD;
        }
        return getCompareResultOf(dealerHand.getCardBundle());
    }

    private double getCompareResultOf(CardBundle targetCardBundle) {
        int compareResult = toScore(cardBundle).compareTo(toScore(targetCardBundle));

        if (compareResult > 0) {
            return WIN_BETTING_YIELD;
        }
        if (compareResult < 0) {
            return LOSE_BETTING_YIELD;
        }
        return DRAW_BETTING_YIELD;
    }

    private Score toScore(CardBundle cardBundle) {
        return cardBundle.getScore();
    }

    @Override
    public String toString() {
        return "Stay{" + "cardBundle=" + cardBundle + '}';
    }
}
