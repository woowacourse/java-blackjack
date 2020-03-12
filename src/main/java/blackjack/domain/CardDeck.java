package blackjack.domain;

import blackjack.exception.CardDeckEmptyException;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private static final int HEAD_INDEX = 0;
    private static final int EMPTY_SIZE = 0;

    private List<Card> cardDeck;

    public CardDeck(List<Card> cards) {
        cardDeck = new ArrayList<>(cards);
    }

    public Card getOneCard() {
        if (isEmpty()) {
            throw new CardDeckEmptyException("카드 덱이 비었습니다.");
        }
        return cardDeck.remove(HEAD_INDEX);
    }

    private boolean isEmpty() {
        return this.cardDeck.size() == EMPTY_SIZE;
    }

    public int size() {
        return this.cardDeck.size();
    }
}
