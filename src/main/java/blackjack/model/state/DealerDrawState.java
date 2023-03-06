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
        return transitStateOrNot();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isStand() {
        return false;
    }

    public State transitStateOrNot() {
        if (isScoreHigherThanBlackjackNumber()) {
            return new BustState(hand);
        }
        if (isScoreHigherThanDealerHitNumber()) {
            return new StandState(hand);
        }
        return this;
    }

    private boolean isScoreHigherThanBlackjackNumber() {
        return hand.getCardScore().getSmallScore() > BLACKJACK_NUMBER;
    }

    private boolean isScoreHigherThanDealerHitNumber() {
        return hand.getCardScore().getBigScore() > DEALER_HIT_NUMBER;
    }
}
