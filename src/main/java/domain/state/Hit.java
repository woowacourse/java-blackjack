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
        if (this.hand.isBust()) {
            return new Bust(this.hand);
        }
        return new Hit(this.hand);
    }

    @Override
    public Result calculateResult(final State dealerState) {
        throw new UnsupportedOperationException();
    }
}
