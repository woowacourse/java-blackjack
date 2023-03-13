package domain.state;

import domain.card.Card;
import domain.card.Hand;

public class Hit extends Running {

    Hit(Card card) {
        this(new Hand(card));
    }

    public Hit(Hand hand) {
        super(hand);
    }


}
