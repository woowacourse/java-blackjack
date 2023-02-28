package domain;

import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private final List<Card> cards;

    private CardRepository(List<Card> cards) {
        this.cards = cards;
    }

    public static CardRepository create() {
        return new CardRepository(initializeCards());
    }

    private static List<Card> initializeCards() {
        List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            initializeCardsByShape(cards, shape);
        }

        return cards;
    }

    private static void initializeCardsByShape(List<Card> cards, Shape shape) {
        for (Number number : Number.values()) {
            cards.add(new Card(shape, number));
        }
    }

    public int size() {
        return cards.size();
    }

    public Card findCardByIndex(int index) {
        return cards.remove(index);
    }
}
