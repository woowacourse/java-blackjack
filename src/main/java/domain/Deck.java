package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cardDeck;

    private Deck(List<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public static Deck initCardDeck() {
        List<Card> cards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            addCard(suit, cards);
        }
        return new Deck(cards);
    }

    private static void addCard(CardSuit suit, List<Card> cards) {
        for (CardRank rank : CardRank.values()) {
            cards.add(new Card(suit, rank));
        }
    }

    public void removeCardOf(int index) {
        cardDeck.remove(index);
    }

    public List<Card> getCardDeck() {
        return List.copyOf(cardDeck);
    }

    public int getDeckSize() {
        return cardDeck.size();
    }

    public Card getCardOf(int index) {
        return cardDeck.get(index);
    }
}
