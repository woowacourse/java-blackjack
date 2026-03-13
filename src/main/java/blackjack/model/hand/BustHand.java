package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public class BustHand extends FinishedHand {

    public BustHand(Collection<Card> existCards, Card newCard) {
        super(existCards, newCard);

        validateBust();
    }

    private void validateBust() {
        if (!isBust()) {
            throw new IllegalStateException("패가 버스트이지 않습니다.");
        }
    }
}
