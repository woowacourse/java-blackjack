package blackjack.model.state;

import blackjack.model.card.CardDeck;
import blackjack.model.participant.Hand;

public class DrawState extends State {
    public DrawState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        hand.add(cardDeck.pick());
        if (isScoreHigherThanBlackjackNumber()) {
            return new BustState(hand);
        }
        return this;
    }

    private boolean isScoreHigherThanBlackjackNumber() {
        return hand.getScore() > BLACKJACK_NUMBER;
    }

    public State transitToStandState() {
        return new StandState(hand);
    }

    public State transitToDealerDrawState() {
        return new DealerDrawState(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
