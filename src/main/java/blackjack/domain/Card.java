package blackjack.domain;

import static java.util.stream.Collectors.toUnmodifiableSet;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class Card {

    public static final Set<Card> VALUES;

    private final Denomination denomination;
    private final Suit suit;

    static {
        VALUES = Arrays.stream(Denomination.values())
                .flatMap(denomination -> Arrays.stream(Suit.values()).map(suit -> new Card(denomination, suit)))
                .collect(toUnmodifiableSet());
    }

    private Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public static Card of(Denomination denomination, Suit suit) {
        return VALUES.stream()
                .filter(card -> card.hasSame(denomination, suit))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
    }

    private boolean hasSame(Denomination denomination, Suit suit) {
        return this.denomination == denomination && this.suit == suit;
    }

    public int getSumScore(int totalScore) {
        return denomination.addScore(totalScore);
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
