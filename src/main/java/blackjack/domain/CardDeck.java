package blackjack.domain;

import blackjack.exception.CardDeckDuplicatedException;
import blackjack.exception.CardDeckEmptyException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardDeck {
    private static final int HEAD_INDEX = 0;

    private List<Card> cardDeck;

    public CardDeck(List<Card> cards) {
        checkCardDeckNull(cards);
        checkCardDeckEmpty(cards);
        checkCardDeckDuplicated(cards);
        cardDeck = new ArrayList<>(cards);
    }

    private void checkCardDeckNull(List<Card> cards) {
        if (cards == null) {
            throw new NullPointerException("생성할 수 있는 카드가 없습니다.");
        }
    }

    private void checkCardDeckDuplicated(List<Card> cards) {
        Set<Card> deDuplicatedCards = new HashSet<>(cards);
        if (deDuplicatedCards.size() != cards.size()) {
            throw new CardDeckDuplicatedException("카드 덱 안에 중복된 카드가 있습니다.");
        }
    }

    public Card getOneCard() {
        checkCardDeckEmpty(cardDeck);
        return cardDeck.remove(HEAD_INDEX);
    }

    private void checkCardDeckEmpty(List<Card> cards) {
        if (cards.isEmpty()) {
            throw new CardDeckEmptyException("카드 덱이 비었습니다.");
        }
    }

    public int size() {
        return this.cardDeck.size();
    }
}
