package blackjack.domain.card;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Cards {

    private static final int CARDS_MINIMUM_CREATE_SIZE = 2;

    private final Set<Card> cards;

    public Cards(final Set<Card> cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        this.cards = new LinkedHashSet<>(cards);
        checkCardsSize(cards);
    }

    private void checkCardsSize(final Set<Card> cards) {
        if (cards.size() < CARDS_MINIMUM_CREATE_SIZE) {
            throw new IllegalArgumentException("cards는 2장이상이 들어와야 합니다.");
        }
    }
}
