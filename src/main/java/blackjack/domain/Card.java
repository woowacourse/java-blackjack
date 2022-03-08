package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {

    private static final List<Card> CARDS_CACHE;

    static {
        CARDS_CACHE = createCards();
    }

    private static List<Card> createCards() {
        final List<CardPattern> patterns = CardPattern.cardPatterns();
        return patterns.stream()
                .map(Card::createPatternCards)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<Card> createPatternCards(final CardPattern pattern) {
        return CardNumber.cardNumbers().stream()
                .map(number -> new Card(pattern, number))
                .collect(Collectors.toList());
    }

    private final CardPattern pattern;
    private final CardNumber number;

    private Card(final CardPattern pattern, final CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public static Card of(final CardPattern pattern, final CardNumber number) {
        return CARDS_CACHE.stream()
                .filter(card -> card.pattern == pattern && card.number == number)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카드입니다."));
    }

    public static List<Card> cards() {
        return new ArrayList<>(CARDS_CACHE);
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
