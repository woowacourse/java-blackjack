package blackjack.domain.card.property;

import java.util.HashMap;
import java.util.Map;

public class CardProperty {
    private static final Map<String, CardProperty> cache = new HashMap<>(52);

    private final CardShape shape;
    private final CardNumber number;

    private CardProperty(CardShape shape, CardNumber number) {
        this.shape = shape;
        this.number = number;
    }

    public static CardProperty of(CardShape shape, CardNumber number) {
        return cache.computeIfAbsent(toKey(shape, number), ignored -> new CardProperty(shape, number));
    }

    private static String toKey(CardShape shape, CardNumber number) {
        return shape.getName() + number.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CardProperty that = (CardProperty)o;

        if (getShape() != that.getShape())
            return false;
        return getNumber() == that.getNumber();
    }

    @Override
    public int hashCode() {
        int result = getShape() != null ? getShape().hashCode() : 0;
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        return result;
    }

    public boolean isA() {
        return number.isA();
    }

    public CardShape getShape() {
        return shape;
    }

    public CardNumber getNumber() {
        return number;
    }
}
