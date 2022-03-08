package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {

    private final CardPattern pattern;
    private final CardNumber number;

    public Card(final CardPattern pattern, final CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public static List<Card> cards() {
        final List<CardPattern> patterns = CardPattern.cardPatterns();
        final List<CardNumber> numbers = CardNumber.cardNumbers();
        final List<Card> cards = new ArrayList<>();
        for (CardPattern pattern : patterns) {
            for (CardNumber number : numbers) {
                cards.add(new Card(pattern, number));
            }
        }
        return cards;
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
