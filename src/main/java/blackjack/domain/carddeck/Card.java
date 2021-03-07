package blackjack.domain.carddeck;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Card {

    private static final Deque<Card> CACHE_DECK = new ArrayDeque<>();

    static {
        for (Pattern pattern : Pattern.values()) {
            for (Number number : Number.values()) {
                CACHE_DECK.add(new Card(pattern, number));
            }
        }
    }

    private final Pattern pattern;
    private final Number number;

    private Card(final Pattern pattern, final Number number) {
        this.pattern = pattern;
        this.number = number;
    }

    public static Card valueOf(final Pattern pattern, final Number number) {
        Card card = CACHE_DECK.stream()
            .filter(cardPatterns -> cardPatterns.hasPattern(pattern))
            .filter(cardNumbers -> cardNumbers.hasNumber(number))
            .findAny()
            .get();
        if (Objects.isNull(card)) {
            throw new IllegalArgumentException("해당 카드가 존재하지 않습니다.");
        }
        return card;
    }

    private boolean hasNumber(final Number number) {
        return this.number.equals(number);
    }

    private boolean hasPattern(final Pattern pattern) {
        return this.pattern.equals(pattern);
    }

    public boolean isAce() {
        return this.number.isAce();
    }

    public int getScore() {
        return this.number.getScore();
    }

    public String getPatternName() {
        return this.pattern.getPattern();
    }

    public String getNumberName() {
        return this.number.getName();
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
        return pattern == card.pattern && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }
}
