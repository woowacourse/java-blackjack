package blackjack.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class Card {

    private final CardPattern pattern;
    private final CardNumber number;

    private Card(final CardPattern pattern, final CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public static Queue<Card> cards() {
        final List<CardPattern> patterns = CardPattern.cardPatterns();
        return patterns.stream()
                .map(Card::createPatternCards)
                .flatMap(List::stream)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static List<Card> createPatternCards(final CardPattern pattern) {
        return CardNumber.cardNumbers().stream()
                .map(number -> new Card(pattern, number))
                .collect(Collectors.toList());
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
