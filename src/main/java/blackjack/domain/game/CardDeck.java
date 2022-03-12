package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {

    private final Stack<Card> cardDeck = new Stack<>();

    public CardDeck() {
        cardDeck.addAll(Card.getTotalCard());
        Collections.shuffle(cardDeck);
    }

    public Card pick() {
        return cardDeck.pop();
    }
}
