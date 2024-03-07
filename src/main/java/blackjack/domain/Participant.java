package blackjack.domain;

import java.util.List;
import java.util.function.BooleanSupplier;

public abstract class Participant {

    protected final Name name;
    protected final Hands hands;

    protected Participant(String name) {
        this.name = new Name(name);
        this.hands = new Hands();
    }

    public abstract void decideDraw(BooleanSupplier supplier, Deck deck);

    public void addCard(Card card) {
        hands.addCard(card);
    }

    public List<Card> getHandsCards() {
        return hands.getHands();
    }
}
