package blackjack.card;

import blackjack.player.Score;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static final Map<String, Card> CARD_POOL = new HashMap<>();

    static {
        initializeCardPool();
    }

    private final Shape shape;
    private final Number number;

    private Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    public static Card of(Shape shape, Number number) {
        String key = shape.name() + number.name();
        return CARD_POOL.get(key);
    }

    private static void initializeCardPool() {
        for (Shape shape : Shape.values()) {
            createNumberCardsOf(shape);
        }
    }

    private static void createNumberCardsOf(Shape shape) {
        for (Number number : Number.values()) {
            CARD_POOL.put(getKeyOf(shape, number), new Card(shape, number));
        }
    }

    private static String getKeyOf(Shape shape, Number number) {
        return shape.name() + number.name();
    }

    public static List<Card> getFullCards() {
        return new ArrayList<>(CARD_POOL.values());
    }

    public boolean isAce() {
        return number == Number.ACE;
    }

    public Score getScore() {
        return Score.of(number.getScore());
    }

    public Shape getShape() {
        return shape;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return shape == card.shape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, number);
    }
}
