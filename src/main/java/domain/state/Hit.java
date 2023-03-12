package domain.state;

import domain.card.Card;
import domain.user.Hand;

public class Hit extends Running {

    public Hit(Card card1, Card card2) {
        super(new Hand(card1, card2));
    }

    public Hit(Card card) {
        this(new Hand(card));
    }

    public Hit(Hand hand) {
        super(hand);
    }
}
