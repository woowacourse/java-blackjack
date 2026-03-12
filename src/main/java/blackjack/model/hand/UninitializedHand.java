package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public class UninitializedHand extends PlayingHand {

    private static final int NEXT_STEP_THRESHOLD = 2;
    private static final int BLACKJACK = 21;

    public UninitializedHand() {
        super();
    }

    private UninitializedHand(Collection<Card> cards) {
        super(cards);
    }

    @Override
    protected Hand nextState(Collection<Card> cards) {
        if (shouldInitialize(cards)) {
            return initialize(cards);
        }

        return new UninitializedHand(cards);
    }

    private boolean shouldInitialize(Collection<Card> cards) {
        return cards.size() >= NEXT_STEP_THRESHOLD;
    }

    private Hand initialize(Collection<Card> cards) {
        if (calculateScore(cards) == BLACKJACK) {
            return new BlackjackHand(cards);
        }
        if (isBust(cards)) {
            return new BustHand(cards);
        }

        return new HitHand(cards);
    }
}
