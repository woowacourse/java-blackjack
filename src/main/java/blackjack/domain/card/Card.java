package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static final Map<String, Card> cache = new HashMap<>(52);

    private final CardPattern pattern;
    private final CardNumber number;

    private Card(final CardPattern pattern, final CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public static Card of(final CardPattern pattern, final CardNumber number) {
        return cache.computeIfAbsent(getKey(pattern, number), ignored -> new Card(pattern, number));
    }

    private static String getKey(final CardPattern pattern, final CardNumber number) {
        return pattern.getName() + number.getPrintValue();
    }

    public CardPattern getPattern() {
        return pattern;
    }

    public CardNumber getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return pattern == card.pattern && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }
}
