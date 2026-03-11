package domain.card;

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

    public List<Card> getCardDeck() {
        return List.copyOf(cardDeck);
    }

    public int getDeckSize() {
        return cardDeck.size();
    }

    public Card draw(int index) {
        return cardDeck.remove(index);
    }
}
