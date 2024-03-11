package blackjack.domain.cardgame;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private final Stack<Card> deck;

    private CardDeck(Stack<Card> deck) {
        this.deck = deck;
    }

    static CardDeck create() {
        final Stack<Card> deck = new Stack<>();
        for (final CardNumber cardNumber : CardNumber.values()) {
            for (final CardShape cardShape : CardShape.values()) {
                deck.push(new Card(cardNumber, cardShape));
            }
        }
        Collections.shuffle(deck);
        return new CardDeck(deck);
    }

    Card draw() {
        if (deck.empty()) {
            throw new IllegalArgumentException("카드가 존재하지 않습니다.");
        }

        return deck.pop();
    }
}
