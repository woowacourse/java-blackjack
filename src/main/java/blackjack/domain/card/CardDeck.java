package blackjack.domain.card;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> cards;

    public CardDeck(final Queue<Card> cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        this.cards = new ArrayDeque<>(cards);
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
