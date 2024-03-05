package domain;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final HoldingCards holdingCards;

    public Player(String name) {
        this.name = name;
        this.holdingCards = new HoldingCards(new ArrayList<>());
    }

    public void draw(Deck deck, CardDrawStrategy cardDrawStrategy) {
        holdingCards.add(deck.draw(cardDrawStrategy));
    }

    public SummationCardPoint getSummationCardPoint() {
        return holdingCards.calculateTotalPoint();
    }
}
