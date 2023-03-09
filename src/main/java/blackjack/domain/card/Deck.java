package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Deck {

    private static final int DECK_SIZE = 52;

    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        validateSize(cards);
        validateDuplicate(cards);
        this.cards = cards;
    }

    private void validateDuplicate(Deque<Card> cards) {
        long duplicatedCardCount = cards.stream()
                .distinct()
                .count();
        if (duplicatedCardCount != cards.size()) {
            throw new IllegalArgumentException("덱에 중복된 카드가 존재합니다.");
        }
    }

    private void validateSize(Deque<Card> cards) {
        if (cards.size() != DECK_SIZE) {
            throw new IllegalArgumentException("덱 크기와 카드 개수가 일치하지 않습니다.");
        }
    }

    public static Deck create(List<Card> cards) {
        Deque<Card> createdCard = new ArrayDeque<>();

        for (Card card : cards) {
            createdCard.push(card);
        }
        return new Deck(createdCard);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 카드가 없습니다.");
        }
        return cards.pop();
    }
}
