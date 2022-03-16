package blackjack.domain.card;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayDeque;
import java.util.List;
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
                .distinct()
                .count();
    }

    public static CardDeck createNewShuffledCardDeck() {
        final List<Card> cards = Card.cards();
        return new CardDeck(new ArrayDeque<>(Randoms.shuffle(cards)));
    }

    public Card provideCard() {
        checkCardDeckEmpty();
        return cards.poll();
    }

    private void checkCardDeckEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드덱이 비어 카드를 제공할 수 없습니다.");
        }
    }
}
