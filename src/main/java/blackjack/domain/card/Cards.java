package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    public static final String INVALID_EMPTY_CARDS_MESSAGE = "카드가 없습니다.";
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.isAce());
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(card -> card.getScore())
                .sum();
    }

    public Card getOneCard() {
        validateIsEmpty();
        return cards.get(0);
    }

    private void validateIsEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(INVALID_EMPTY_CARDS_MESSAGE);
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getSize() {
        return cards.size();
    }
}
