package blackjack.model.card;

import java.util.*;

public class CardDeck {

    private final Stack<Card> cardDeck;

    public CardDeck() {
        cardDeck = new Stack<>();
        List<Card> cards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            createCardsWithSuit(cards, suit);
        }
        Collections.shuffle(cards);
        cards.forEach(cardDeck::push);
    }

    private static void createCardsWithSuit(List<Card> cards, CardSuit suit) {
        for (CardNumber number : CardNumber.values()) {
            cards.add(Card.of(suit, number));
        }
    }

    public CardDeck(List<Card> cards) {
        cardDeck = new Stack<>();
        cards.forEach(cardDeck::push);
    }

    public Card pick() {
        return cardDeck.pop();
    }
}
