package blackjack.domain;

import blackjack.domain.card.Card;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> cards;

    private CardDeck(final Queue<Card> cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        this.cards = new ArrayDeque<>(cards);
    }

    public static CardDeck createNewCardDek() {
        List<Card> cards = Card.createNewCards();
        return new CardDeck(new ArrayDeque<>(Randoms.shuffle(cards)));
    }

    public Card provideCard() {
        validateEmptyDeck();
        return cards.poll();
    }

    private void validateEmptyDeck() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("남은 카드가 없습니다.");
        }
    }
}
