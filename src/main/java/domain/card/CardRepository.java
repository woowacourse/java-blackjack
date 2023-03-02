package domain.card;

import domain.strategy.IndexGenerator;

import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private final List<Card> cards;
    private final IndexGenerator indexGenerator;


    private CardRepository(List<Card> cards, IndexGenerator indexGenerator) {
        this.cards = cards;
        this.indexGenerator = indexGenerator;
    }

    public static CardRepository create(IndexGenerator indexGenerator) {
        return new CardRepository(initializeCards(), indexGenerator);
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

    public Card findAnyOneCard() {
        int index = this.indexGenerator.generate(size());
        return cards.remove(index);
    }
}
