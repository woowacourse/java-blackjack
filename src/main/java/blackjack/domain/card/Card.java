package blackjack.domain.card;

import java.util.*;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.values;

public class Card {

    private static final List<Card> cards = new ArrayList<>();

    static {
        for (Suit suit : Suit.values()) {
            Arrays.stream(values())
                    .forEach(denomination -> cards.add(new Card(suit, denomination)));
        }
    }

    private final Suit suit;
    private final Denomination denomination;

    private Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card of(final Suit suit, final Denomination denomination) {
        return cards.stream()
                .filter(card -> card.equals(new Card(suit, denomination)))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("유효하지 않은 카드입니다."));
    }

    public static LinkedList<Card> cashingCards() {
        return new LinkedList<>(cards);
    }

    public String denomination() {
        return denomination.getDenomination();
    }

    public Score getCardScore() {
        return denomination.getScore();
    }

    public String suit() {
        return suit.getSuit();
    }

    public boolean isAce() {
        return denomination == ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return denomination == card.denomination && Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
