package blackjack.model.state;

import blackjack.model.card.CardDeck;
import blackjack.model.participant.Hand;

public class PlayerDrawState extends DrawState {
    public PlayerDrawState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        hand.add(cardDeck.pick());
        return tryStateTransition();
    }

    private State tryStateTransition() {
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

}
