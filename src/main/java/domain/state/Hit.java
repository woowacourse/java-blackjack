package domain.state;

import domain.Result;
import domain.card.Card;

public class Hit extends Running {

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State drawCard(Card card) {
        this.hand.addCard(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        return new Hit(hand);
    }

    @Override
    public Result calculateResult() {
        throw new UnsupportedOperationException();
    }
}
