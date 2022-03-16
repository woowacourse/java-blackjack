package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Queue;

public class CardDeck {

    private static final int CARD_DECK_DEFAULT_SIZE = 52;

    private final Queue<Card> cards;

    public CardDeck(final Queue<Card> cards) {
        checkCardDeckSize(cards);
        this.cards = new ArrayDeque<>(cards);
    }

    private void checkCardDeckSize(final Queue<Card> cards) {
        if (cardsDistinctCount(cards) != CARD_DECK_DEFAULT_SIZE) {
            throw new IllegalArgumentException("카드 덱은 중복되지 않은 52장으로만 생성할 수 있습니다.");
        }
    }

    private long cardsDistinctCount(final Queue<Card> cards) {
        return cards.stream()
                .distinct().count();
    }
}
