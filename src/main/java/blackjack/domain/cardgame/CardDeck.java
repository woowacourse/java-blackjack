package blackjack.domain.cardgame;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private final Stack<Card> deck;

    public CardDeck() {
        final Stack<Card> deck = new Stack<>();
        // TODO: 2중 for문 제거
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
