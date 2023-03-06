package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.HandCard;

public class DealerInitialState extends InitialState {
    private static final int DEALER_HIT_NUMBER = 16;
    private static final int PICK_COUNT = 2;

    public DealerInitialState(HandCard handCard) {
        super(handCard);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        for (int i = 0; i < PICK_COUNT; i++) {
            Card picked = cardDeck.pick();
            handCard.add(picked);
        }
        if (handCard.isBigScoreEqual(BLACKJACK_NUMBER)) {
            return new BlackjackState(handCard);
        }
        if (isFinished()) {
            return new StandState(handCard);
        }
        return new DealerDrawState((handCard));
    }

    @Override
    public boolean isFinished() {
        return handCard.isBigScoreOver(DEALER_HIT_NUMBER);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
