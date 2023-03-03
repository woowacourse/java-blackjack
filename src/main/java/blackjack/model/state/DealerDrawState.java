package blackjack.model.state;

import blackjack.Hand;
import blackjack.model.card.CardDeck;

public class DealerDrawState extends State{
    private static final int DEALER_HIT_NUMBER = 16;

    public DealerDrawState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        hand.add(cardDeck.pick());

        if (hand.score().smallScore() > BLACKJACK_NUMBER && hand.score().bigScore() > BLACKJACK_NUMBER) {
            return new BustState(hand);
        }
        if (!isFinished()) {
            return this;
        }
        return new StandState(hand);
    }

    @Override
    public boolean isFinished() {
        return hand.score().bigScore() > DEALER_HIT_NUMBER;
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
}
