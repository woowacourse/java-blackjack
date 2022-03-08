package model;

import java.util.ArrayList;
import java.util.List;

public class Participator {
    private final List<Card> cards;

    public Participator() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
