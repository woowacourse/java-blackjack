package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Card {

    private static final List<Card> BASIC_CARDS = createCards();

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

    public static List<Card> cards() {
        return new ArrayList<>(BASIC_CARDS);
    }

    public CardPattern getPattern() {
        return pattern;
    }

    public CardNumber getNumber() {
        return number;
    }
}
