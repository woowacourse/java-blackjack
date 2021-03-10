package blackjack.domain.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Card {

    private static final Set<Card> CARD_POOL;

    static {
        CARD_POOL = createCardPool();
    }

    private final Shape shape;
    private final CardValue value;

    private Card(Shape shape, CardValue value) {
        this.shape = shape;
        this.value = value;
    }

    private static Set<Card> createCardPool() {
        Set<Card> cardPool = new HashSet<>();
        for (Shape shape : Shape.values()) {
            Arrays.stream(CardValue.values())
                .forEach(value -> cardPool
                    .add(new Card(shape, value)));
        }
        return cardPool;
    }

    public static Card valueOf(Shape shape, CardValue value) {
        return CARD_POOL.stream()
            .filter(card -> card.shape == shape && card.value == value)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public static List<Card> getAllCards() {
        return new ArrayList<>(CARD_POOL);
    }

    public int getScore() {
        return value.getValue();
    }

    public String getCardName() {
        return this.value.getName() + this.shape.getName();
    }

    public boolean hasMultipleValue() {
        return value.hasMultipleValue();
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
        return shape == card.shape &&
            value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, value);
    }
}
