package domain;

import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private static final List<Card> cards = new ArrayList<>();

    private CardRepository() {}

    public static void init() {
        initializeCards();
    }

    private static void initializeCards() {
        for (Shape shape : Shape.values()) {
            initializeCardsByShape(shape);
        }
    }

    private static void initializeCardsByShape(Shape shape) {
        for (Number number : Number.values()) {
            cards.add(new Card(shape, number));
        }
    }

    public static int size() {
        return cards.size();
    }

    public static Card findCardByIndex(int index) {
        return cards.remove(index);
    }
}
