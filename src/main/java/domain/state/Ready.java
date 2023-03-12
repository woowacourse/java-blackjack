package domain.state;

import domain.card.Card;
import domain.card.Hand;

public final class Ready extends State {
    public Ready() {
        super(new Hand());
    }

    public Ready(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        if (getHand().isEmpty()) {
            return new Ready(getHand().add(card));
        }
        return new Hit(getHand().add(card));
    }

    @Override
    public List<Card> getCards() {
        return hand.getCards();
    }
}
