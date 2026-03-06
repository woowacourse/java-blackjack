package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
