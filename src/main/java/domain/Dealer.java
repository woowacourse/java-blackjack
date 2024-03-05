package domain;

import java.util.ArrayList;

public class Dealer {
    private final HoldingCards holdingCards;

    public Dealer() {
        this.holdingCards = new HoldingCards(new ArrayList<>());
    }

    public void draw(Deck deck, CardDrawStrategy cardDrawStrategy) {
        holdingCards.add(deck.draw(cardDrawStrategy));
    }

    public SummationCardPoint getSummationCardPoint() {
        return holdingCards.calculateTotalPoint();
    }
}
