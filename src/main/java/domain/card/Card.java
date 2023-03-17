package domain.card;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class Card {
    private static final int CARD_SIZE = 52;
    private static final Map<String, Card> CACHE = new ConcurrentHashMap<>(CARD_SIZE);

    private final CardPattern pattern;
    private final CardNumber number;

    private Card(final CardPattern pattern, final CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public static Card create(final CardPattern pattern, final CardNumber number) {
        return CACHE.computeIfAbsent(toKey(pattern, number), mapping -> new Card(pattern, number));
    }

    private static String toKey(final CardPattern pattern, final CardNumber number) {
        return pattern.name() + number.name();
    }

    public boolean isAce() {
        return number.isAce();
    }

    public Score findCardScore() {
        return number.getScore();
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Card)) {
            return false;
        }
        Card targetCard = (Card) target;
        return pattern == targetCard.pattern && number == targetCard.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }

    public CardPattern getCardPattern() {
        return pattern;
    }

    public CardNumber getCardNumber() {
        return number;
    }
}
