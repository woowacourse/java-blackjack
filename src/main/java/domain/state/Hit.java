package domain.state;

import domain.card.Card;
import domain.card.Hand;

public final class Hit extends State {
    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        final Hand newHand = getHand().add(card);
        if(newHand.isBust())
            return new Bust(newHand);
        return new Hit(getHand().add(card));
    }

    public State stay() {
        return new Stay(getHand());
    }
}
