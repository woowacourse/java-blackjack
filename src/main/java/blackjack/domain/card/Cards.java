package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {

    private static final int BLACKJACK_CARDS_SIZE = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        checkCardsSize(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void checkCardsSize(final List<Card> cards) {
        if (cardsDistinctCount(cards) < BLACKJACK_CARDS_SIZE) {
            throw new IllegalArgumentException("cards는 2장이상이 들어와야 합니다.");
        }
    }

    private int cardsDistinctCount(final List<Card> cards) {
        return (int) cards.stream()
                .distinct()
                .count();
    }

    public Score score() {
        return Denomination.calculateCardScore(cards);
    }

    public boolean isBust() {
        return score().isBust();
    }

    public boolean isBlackjack() {
        return score().isBlackjack() && cards.size() == BLACKJACK_CARDS_SIZE;
    }

    public Score maxScore() {
        return Denomination.calculateCardMaxScore(cards);
    }

    public boolean isMaxScoreBust() {
        return maxScore().isBust();
    }

    public boolean isDealerStandScore() {
        return maxScore().isDealerStand();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }
}
