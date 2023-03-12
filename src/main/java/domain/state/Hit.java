package domain.state;

import domain.card.Card;
import domain.user.Hand;

public class Hit extends Running {

    public Hit(Card card) {
        this(new Hand(card));
    }

    public Hit(Hand hand) {
        super(hand);
    }


}
