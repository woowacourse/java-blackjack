package domain.participant;

import domain.Score;
import domain.card.Card;
import domain.card.Hand;
import domain.state.Hit;
import domain.state.State;

public class Dealer extends Participant {
    private Dealer(State state) {
        super(state);
    }

    public static Dealer createReady(Hand hand) {
        return new Dealer(Hit.of(hand));
    }

    public Card getFirstCard() {
        return state.hand().peek();
    }

    public boolean isHittable() {
        return state.hand().totalSum().isLessThanOrEqualTo(Score.DEALER_HIT_STAND_BOUNDARY);
    }
}
