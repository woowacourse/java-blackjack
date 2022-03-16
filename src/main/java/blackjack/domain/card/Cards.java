package blackjack.domain.card;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Cards {

    public static final int BLACKJACK_TARGET_NUMBER = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final Set<Card> cards;

    public Cards(final Set<Card> cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        checkCardsSize(cards);
        this.cards = new LinkedHashSet<>(cards);
    }

    private void checkCardsSize(final Set<Card> cards) {
        if (cards.size() < BLACKJACK_CARD_SIZE) {
            throw new IllegalArgumentException("cards는 2장이상이 들어와야 합니다.");
        }
    }

    public boolean isBust() {
        return Denomination.calculateCardScore(cards) > BLACKJACK_TARGET_NUMBER;
    }

    public boolean isBlackjack() {
        System.out.println(Denomination.calculateCardScore(cards));
        return cards.size() == BLACKJACK_CARD_SIZE && Denomination.calculateCardScore(cards) == BLACKJACK_TARGET_NUMBER;
    }
}
