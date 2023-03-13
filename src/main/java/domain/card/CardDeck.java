package domain.card;

import java.util.*;

public class CardDeck {

    private final Deque<Card> cardDeck;

    private CardDeck(Deque<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public static CardDeck generateCardDeck() {
        List<Card> cards = new ArrayList<>();
        makeCards(cards);
        shuffleCards(cards);
        Deque<Card> cardDeck = new ArrayDeque<>(cards);
        return new CardDeck(cardDeck);
    }

    private static void makeCards(List<Card> cards) {
        for (CardType cardType : CardType.values()) {
            addCards(cards, cardType);
        }
    }

    private static void addCards(List<Card> cards, CardType cardType) {
        for (CardValue cardValue : CardValue.values()) {
            cards.add(new Card(cardType, cardValue));
        }
    }

    private static void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public Card pickCard() {
        return cardDeck.pop();
    }

    public Deque<Card> getCardDeck() {
        return cardDeck;
    }
}
