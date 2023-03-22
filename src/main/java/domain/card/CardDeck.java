package domain.card;

import java.util.*;

public class CardDeck {
    private static Deque<Card> cardDeck;

    static {
        cardDeck = makeCards();
    }

    private static Deque<Card> makeCards() {
        Deque<Card> cards = new ArrayDeque<>();
        for (CardType cardType : CardType.values()) {
            addCards(cards, cardType);
        }
        return cards;
    }

    private static void addCards(Deque<Card> cards, CardType cardType) {
        for (CardValue cardValue : CardValue.values()) {
            cards.add(new Card(cardType, cardValue));
        }
    }

    public CardDeck() {
        List<Card> cardDeck = new ArrayList<>(CardDeck.cardDeck);
        Collections.shuffle(cardDeck);
        CardDeck.cardDeck = new ArrayDeque<>(cardDeck);
    }

    public Card pickCard() {
        return cardDeck.pop();
    }
}
