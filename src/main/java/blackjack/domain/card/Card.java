package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {

    private static final List<Card> BASIC_CARDS = createBasicCards();

    private final CardPattern pattern;
    private final CardNumber number;

    private Card(final CardPattern pattern, final CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public static Card of(final CardPattern pattern, final CardNumber number) {
        return BASIC_CARDS.stream()
                .filter(card -> isEqualCard(pattern, number, card))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
    }

    private static boolean isEqualCard(final CardPattern pattern, final CardNumber number, final Card card) {
        return card.pattern == pattern && card.number == number;
    }

    private static List<Card> createBasicCards() {
        final List<CardPattern> patterns = CardPattern.allPatterns();
        return patterns.stream()
                .map(Card::createPatternCards)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<Card> createPatternCards(final CardPattern pattern) {
        return CardNumber.allNumbers().stream()
                .map(number -> new Card(pattern, number))
                .collect(Collectors.toList());
    }

    public static List<Card> getAllCards() {
        return new ArrayList<>(BASIC_CARDS);
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
