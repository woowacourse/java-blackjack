package blackJack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static final int INITIAL_CAPACITY = 52;
    private static final Map<String, Card> CARDS = new HashMap<>(INITIAL_CAPACITY);

    private final Suit suit;
    private final Denomination denomination;

    private Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public static Card valueOf(Suit suit, Denomination denomination) {
        return CARDS.computeIfAbsent(getCardKey(suit, denomination), key ->
                new Card(suit, denomination));
    }

    private static String getCardKey(Suit suit, Denomination denomination) {
        return suit.getName() + denomination.getDenomination();
    }

    public boolean isSameDenominationAsAce() {
        return denomination == Denomination.ACE;
    }

    public String getSymbolName() {
        return suit.getName();
    }

    public int getScore() {
        return denomination.getScore();
    }

    public String getDenominationName() {
        return denomination.getDenomination();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
