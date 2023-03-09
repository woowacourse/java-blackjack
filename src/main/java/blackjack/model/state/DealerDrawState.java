package blackjack.model.state;

import blackjack.model.card.CardDeck;
import blackjack.model.participant.Hand;

public class DealerDrawState extends State {
    private static final int DEALER_HIT_NUMBER = 16;

    public DealerDrawState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        hand.add(cardDeck.pick());
        return tryStateTransition();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public State tryStateTransition() {
        if (isScoreHigherThanBlackjackNumber()) {
            return new BustState(hand);
        }
        if (isScoreHigherThanDealerHitNumber()) {
            return new StandState(hand);
        }
        return this;
    }

    private boolean isScoreHigherThanBlackjackNumber() {
        return hand.getScore() > BLACKJACK_NUMBER;
    }

    private boolean isScoreHigherThanDealerHitNumber() {
        return hand.getScore() > DEALER_HIT_NUMBER;
    }
}
