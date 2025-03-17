package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.card.CardHand;

public class Blackjack extends Finished{

    public Blackjack(CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult determineResult(State otherState) {
        if (otherState.isBlackjack()) {
            return GameResult.DRAW;
        }
        return GameResult.BLACKJACK_WIN;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
