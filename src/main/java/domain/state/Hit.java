package domain.state;

import domain.card.Card;
import domain.card.Hand;

public class Hit extends Running {

    Hit(final Card card) {
        this(new Hand(card));
    }

    public Hit(final Hand hand) {
        super(hand);
    }


}
