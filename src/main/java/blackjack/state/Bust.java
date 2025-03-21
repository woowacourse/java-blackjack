package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.card.CardHand;

public class Bust extends Finished{

    public Bust(CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult determineResult(HandState otherHandState) {
        return GameResult.LOSE;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}
