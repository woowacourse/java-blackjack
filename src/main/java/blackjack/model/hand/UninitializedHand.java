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
    protected Hand nextState() {
        if (shouldInitialize()) {
            return initialize();
        }

        return new UninitializedHand(cards);
    }

    private boolean shouldInitialize() {
        return cards.size() >= NEXT_STEP_THRESHOLD;
    }

    private Hand initialize() {
        if (calculateScore() == BLACKJACK) {
            return new BlackjackHand(cards);
        }
        if (isBust()) {
            return new BustHand(cards);
        }

        return new HitHand(cards);
    }
}
