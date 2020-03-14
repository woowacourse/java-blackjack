package blackjack.domain.card;

import blackjack.exception.CardDeckEmptyException;

import java.util.List;
import java.util.Stack;

public class CardDeck {
    private Stack<Card> cardDeck;

    public CardDeck(List<Card> cards) {
        cardDeck = new Stack<>();
        cardDeck.addAll(cards);
    }

    public Card pop() {
        if (cardDeck.isEmpty()) {
            throw new CardDeckEmptyException("카드 덱이 비었습니다.");
        }
        return cardDeck.pop();
    }

    public int size() {
        return this.cardDeck.size();
    }
}
