package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.card.CardHand;

public class Blackjack extends Finished{

    public Blackjack(CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult determineResult(HandState otherHandState) {
        if (otherHandState.isBlackjack()) {
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
