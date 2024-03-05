package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Name name;
    private final Deck deck;

    public Player(Name name) {
        this.name = name;
        this.deck = new Deck();
    }

    public void receiveCard(Card card) {
        deck.addCard(card);
    }

    public Name getName() {
        return name;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean isNotOver(int boundaryScore) {
        return deck.calculateTotalScore() < boundaryScore;
    }
}
