package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {

    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        checkCardsSize(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void checkCardsSize(final List<Card> cards) {
        if (cardsDistinctCount(cards) < BLACKJACK_CARD_SIZE) {
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
        return score().isBustScore();
    }

    public boolean isBlackjack() {
        return score().isBlackjackScore() && cards.size() == BLACKJACK_CARD_SIZE;
    }

    public Score maxScore() {
        return Denomination.calculateCardMaxScore(cards);
    }

    public boolean isMaxScoreBust() {
        return maxScore().isBustScore();
    }

    public boolean isDealerStandScore() {
        return maxScore().isDealerStandScore();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }

    public Score createBlackjackScore() {
        checkIsBlackjack();
        return score();
    }

    private void checkIsBlackjack() {
        if (!isBlackjack()) {
            throw new IllegalStateException("블랙잭이 아니면 생성 불가능합니다.");
        }
    }
}
