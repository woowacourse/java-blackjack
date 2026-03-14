package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;
import java.util.List;

public class UninitializedHand extends PlayingHand {

    private static final int NEXT_STEP_THRESHOLD = 2;

    public UninitializedHand() {
        super();
    }

    private UninitializedHand(Collection<Card> cards) {
        super(cards);
    }

    @Override
    public Hand hit(Card newCard) {
        if (shouldInitialize()) {
            return initialize(newCard);
        }

        return new UninitializedHand(getNextCards(newCard));
    }

    @Override
    public double getEarningRate() {
        return 1;
    }

    private boolean shouldInitialize() {
        return cards.size() + 1 >= NEXT_STEP_THRESHOLD;
    }

    private Hand initialize(Card newCard) {
        List<Card> nextCards = getNextCards(newCard);
        if (isBlackjackWith(newCard)) {
            return new BlackjackHand(nextCards);
        }
        if (isBustWith(newCard)) {
            return new BustHand(nextCards);
        }

        return new HitHand(nextCards);
    }
}
