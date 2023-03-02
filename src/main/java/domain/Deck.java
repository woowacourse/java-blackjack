package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        this.cards = generateCards();
    }

    private List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        for (CardNumber value : CardNumber.values()) {
            generateCard(cards, value);
        }
        return cards;
    }

    private void generateCard(List<Card> cards, CardNumber value) {
        for (CardShape cardShape : CardShape.values()) {
            Card card = new Card(value, cardShape);
            cards.add(card);
        }
    }

    public int getSize() {
        return this.cards.size();
    }
}
