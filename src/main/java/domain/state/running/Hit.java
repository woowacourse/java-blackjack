package domain.state.running;

import domain.card.Hand;
import domain.card.vo.Card;
import domain.state.State;
import domain.state.finished.Bust;

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
        return new Hit(hand);
    }
}
