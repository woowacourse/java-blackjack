package blackjack.domain;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private final Stack<Card> deck;

    public CardDeck() {
        final Stack<Card> deck = new Stack<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            for (CardShape cardShape : CardShape.values()) {
                deck.push(new Card(cardNumber, cardShape));
            }
        }

        Collections.shuffle(deck);

        this.deck = deck;
    }

    public Card draw() {
        return deck.pop();
    }
}
