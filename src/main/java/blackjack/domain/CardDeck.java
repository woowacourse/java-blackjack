package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> cardDeck;

    public CardDeck(LinkedList<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Card drawCard() {
        checkEmptyCardDeck();
        return cardDeck.poll();
    }

    private void checkEmptyCardDeck() {
        if (cardDeck.isEmpty()) {
            throw new IllegalArgumentException("카드덱에 남은 카드가 없습니다.");
        }
    }

    public int size() {
        return cardDeck.size();
    }
}
