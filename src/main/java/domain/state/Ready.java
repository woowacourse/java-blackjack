package domain.state;

import domain.card.Card;
import domain.card.Hand;

import java.util.List;

public final class Ready implements State {
    private final Hand hand;

    public Ready() {
        this.hand = new Hand();
    }

    public Ready(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(Card card) {
        if (hand.isEmpty()) {
            return new Ready(hand.add(card));
        }
        return new Hit(hand.add(card));
    }

    @Override
    public List<Card> getCards() {
        return hand.getCards();
    }
}
