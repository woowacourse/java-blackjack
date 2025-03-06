package blackjack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {

    private final Suit suit;
    private final CardValue cardValue;

    public Card(Suit suit, CardValue cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }

    public static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            addCardsForSuit(suit, deck);
        }
        return deck;
    }

    private static void addCardsForSuit(Suit suit, List<Card> deck) {
        for (CardValue cardValue : CardValue.values()) {
            deck.add(new Card(suit, cardValue));
        }
    }

    public String getDisplayLabel() {
        return cardValue.getLabel() + suit.getLabel();
    }

    public CardValue getCardValue() {
        return cardValue;
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
        return suit == card.suit
                && cardValue == card.cardValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, cardValue);
    }
}
