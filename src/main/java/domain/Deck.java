package domain;

import domain.constants.Score;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final List<Card> cards = new ArrayList<>();

    static {
        for (Shape shape : Shape.values()) {
            createCardOf(shape);
        }
    }

    private static void createCardOf(final Shape shape) {
        for (Score score : Score.values()) {
            cards.add(new Card(score, shape));
        }
    }

    private Deck() {
    }

    public static void shuffle() {
        Collections.shuffle(cards);
    }

    public static Card pick() {
        validateCardQuantity();
        return cards.remove(0);
    }

    private static void validateCardQuantity() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 모두 소진되었습니다.");
        }
    }

    public static int getTotalSize() {
        return cards.size();
    }
}
