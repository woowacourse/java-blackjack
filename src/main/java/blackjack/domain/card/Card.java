package blackjack.domain.card;

import java.util.*;

public class Card {

    private static final Map<String, Card> CARDS = new HashMap<>();

    static {
        for (Suit suit : Suit.values()) {
            addCard(suit);
        }
    }

    private final Suit suit;
    private final Denomination denomination;

    private Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    private static void addCard(Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            CARDS.put(denomination.getDenomination().concat(suit.getName()), new Card(suit, denomination));
        }
    }

    public static Card from(Suit suit, Denomination denomination) {
        return CARDS.get(denomination.getDenomination().concat(suit.getName()));
    }

    public static List<Card> initializeDeck() {
        return new ArrayList<>(CARDS.values());
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public String getSymbolName() {
        return suit.getName();
    }

    public String getDenominationName() {
        return denomination.getDenomination();
    }

    public int getScore() {
        return denomination.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Card))
            return false;
        Card card = (Card) o;
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }
}
