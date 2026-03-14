package domain.state.running;

import domain.card.Hand;
import domain.card.vo.Card;
import domain.state.State;
import domain.state.finished.Bust;
import domain.state.finished.Stay;

public class Hit extends Running {

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State drawCard(Card card) {
        hand.add(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        if (isFinished()) {
            return new Stay(hand);
        }
        return new Hit(hand);
    }
}
