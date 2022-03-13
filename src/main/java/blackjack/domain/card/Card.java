package blackjack.domain.card;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class Card {

    public static final Set<Card> VALUES = createCards();

    private final Denomination denomination;
    private final Suit suit;

    private Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    private static Set<Card> createCards() {
        return Arrays.stream(Denomination.values())
                .map(Card::createIntactCards)
                .flatMap(Set::stream)
                .collect(toUnmodifiableSet());
    }

    private static Set<Card> createIntactCards(Denomination denomination) {
        return Arrays.stream(Suit.values())
                .map(suit -> new Card(denomination, suit))
                .collect(toSet());
    }

    public static Card of(Denomination denomination, Suit suit) {
        return VALUES.stream()
                .filter(card -> card.isSame(denomination, suit))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
    }

    private boolean isSame(Denomination denomination, Suit suit) {
        return this.denomination == denomination && this.suit == suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Suit getSuit() {
        return suit;
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
        return denomination == card.denomination && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }
}
