package blackjack.domain.card;

import java.util.*;

public class Card {

    private static final List<Card> CACHED_CARDS = new LinkedList<>();

    static {
        for (Suit suit : Suit.values()) {
            Arrays.stream(Denomination.values())
                    .forEach(denomination -> CACHED_CARDS.add(new Card(suit, denomination)));
        }
    }

    private final Suit suit;
    private final Denomination denomination;

    public Card(final Suit suit, final Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Deque<Card> getShuffledCards() {
        List<Card> cards = new LinkedList<>(CACHED_CARDS);
        Collections.shuffle(cards);
        return new LinkedList<>(cards);
    }

    public Score getScore() {
        return denomination.getScore();
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public String getDenominationValue() {
        return denomination.denomination();
    }

    public String getSuitValue() {
        return suit.suit();
    }

    @Override
    public boolean equals(final Object o) {
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
