package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public class BlackjackHand extends FinishedHand {

    public BlackjackHand(Collection<Card> cards) {
        super(cards);

        validateBlackjack();
    }

    @Override
    public double getEarningRate() {
        return 1.5;
    }

    private void validateBlackjack() {
        if (!isBlackjack()) {
            throw new IllegalStateException("패가 블랙잭이지 않습니다.");
        }
    }
}
