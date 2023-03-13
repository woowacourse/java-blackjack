package domain.state;

import domain.card.Card;

public class Hit extends Running {

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        return null;
    }
}
