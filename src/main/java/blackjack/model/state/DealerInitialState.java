package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.participant.Hand;

public class DealerInitialState extends InitialState {

    private static final int DEALER_HIT_NUMBER = 16;

    public DealerInitialState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        for (int i = 0; i < PICK_COUNT; i++) {
            Card picked = cardDeck.pick();
            hand.add(picked);
        }
        return tryStateTransition();
    }

    private State tryStateTransition() {
        if (isScoreBlackjackNumber()) {
            return new BlackjackState(hand);
        }
        if (isScoreHigherThanDealerHitNumber()) {
            return new StandState(hand);
        }
        return new DealerDrawState(hand);
    }

    private boolean isScoreBlackjackNumber() {
        return hand.getScore() == BLACKJACK_NUMBER;
    }

    private boolean isScoreHigherThanDealerHitNumber() {
        return hand.getScore() > DEALER_HIT_NUMBER;
    }

}
