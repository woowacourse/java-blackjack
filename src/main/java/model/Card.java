package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {
    private final CardSuit cardSuit;
    private final CardFace cardFace;

    public Card(CardSuit cardSuit, CardFace cardFace) {
        this.cardSuit = cardSuit;
        this.cardFace = cardFace;
    }

    public static List<Card> getAllCards() {
        List<Card> allCards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardFace face : CardFace.values()) {
                allCards.add(new Card(suit, face));
            }
        }
        return allCards;
    }

    public int getCardScore() {
        return cardFace.getCardScore();
    }

    public boolean isAceCard() {
        return this.cardFace == CardFace.ACE;
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
        return cardSuit == card.cardSuit && cardFace == card.cardFace;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSuit, cardFace);
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public CardFace getCardFace() {
        return cardFace;
    }
}
