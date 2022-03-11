package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private static final int INITIAL_CARD_COUNT = 2;

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

    public Cards drawInitialCards() {
        List<Card> initialCards = new ArrayList<>(INITIAL_CARD_COUNT);
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            initialCards.add(cardDeck.poll());
        }
        return new Cards(initialCards);
    }
}
