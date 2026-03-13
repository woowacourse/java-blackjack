package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public class UninitializedHand extends PlayingHand {

    private static final int NEXT_STEP_THRESHOLD = 2;

    public UninitializedHand() {
        super();
    }

    private UninitializedHand(Collection<Card> existCards, Card newCard) {
        super(existCards, newCard);
    }

    @Override
    public Hand hit(Card newCard) {
        if (shouldInitialize()) {
            return initialize(newCard);
        }

        return new UninitializedHand(cards, newCard);
    }

    private boolean shouldInitialize() {
        return cards.size() + 1 >= NEXT_STEP_THRESHOLD;
    }

    private Hand initialize(Card newCard) {
        if (isBlackjackWith(newCard)) {
            return new BlackjackHand(cards, newCard);
        }
        if (isBustWith(newCard)) {
            return new BustHand(cards, newCard);
        }

        return new HitHand(cards, newCard);
    }
}
