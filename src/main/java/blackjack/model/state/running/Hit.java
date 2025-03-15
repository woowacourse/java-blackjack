package blackjack.model.state.running;

import blackjack.model.card.Card;
import blackjack.model.state.Hand;
import blackjack.model.state.State;
import blackjack.model.state.finished.Bust;
import blackjack.model.state.finished.Stand;

public final class Hit extends RunningState {

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State receiveCard(Card card) {
        Hand newHand = hand.add(card);
        if (newHand.getTotal() == MAX_SCORE) {
            return new Stand(newHand);
        }
        if (MAX_SCORE < getTotal()) {
            return new Bust(newHand);
        }
        return new Hit(newHand);
    }
}
