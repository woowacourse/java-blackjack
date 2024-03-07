package blackjack.domain;

import java.util.List;
import java.util.function.BooleanSupplier;

public class Dealer {
    private static final int DEALER_DRAW_THRESHOLD = 17;

    private final Name name;
    private final Hands hands;

    public Dealer(String name) {
        this.name = new Name(name);
        this.hands = new Hands();
    }

    public void decideDraw(BooleanSupplier supplier, Deck deck) {
        if (supplier.getAsBoolean()) {
            Card card = deck.draw();
            hands.addCard(card);
        }
    }

    public Boolean shouldDraw() {
        return hands.getHandsScore() < DEALER_DRAW_THRESHOLD;
    }

    public void addCard(Card card) {
        hands.addCard(card);
    }

    public List<Card> getHandsCards() {
        return hands.getHands();
    }
}
