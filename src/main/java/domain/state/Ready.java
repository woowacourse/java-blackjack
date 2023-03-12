package domain.state;

import domain.card.Card;
import domain.card.Hand;

import java.util.List;

public final class Ready implements State{
    private final Hand hand;

    public Ready() {
        this.hand = new Hand();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
