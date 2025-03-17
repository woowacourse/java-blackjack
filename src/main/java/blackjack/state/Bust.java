package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;

public class Bust extends Finished{

    public Bust(CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult determineResult(State otherState) {
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
