package domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private final List<Card> cardDeck;

    private CardDeck(List<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public static CardDeck initCardDeck() {
        List<Card> cards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        return new CardDeck(cards);
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

    public void removeCardOf(int index) {
        cardDeck.remove(index);
    }
}
