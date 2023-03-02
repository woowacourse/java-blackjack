package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;
    private int usedCount = 0;


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

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.get(usedCount++);
    }

    public int getSize() {
        return this.cards.size();
    }
}
