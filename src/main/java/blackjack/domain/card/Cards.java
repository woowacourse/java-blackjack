package blackjack.domain.card;

import static blackjack.domain.state.Blackjack.BLACKJACK_TARGET_NUMBER;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Cards {

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

    public int score() {
        return Denomination.calculateCardScore(cards);
    }

    public boolean isBust() {
        return score() > BLACKJACK_TARGET_NUMBER;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_SIZE && score() == BLACKJACK_TARGET_NUMBER;
    }

    public int maxScore() {
        return Denomination.calculateCardMaxScore(cards);
    }

    public boolean isMaxScoreBust() {
        return maxScore() > BLACKJACK_TARGET_NUMBER;
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }
}
