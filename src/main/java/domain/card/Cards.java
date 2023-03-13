package domain.card;

import domain.result.WinningStatus;
import domain.score.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final Score BUST_BOUNDARY_EXCLUSIVE = new Score(21);
    private static final Score BONUS_SCORE = new Score(10);
    private static final int INITIAL_CARDS_SIZE = 2;
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = Collections.unmodifiableList(cards);
    }

    public Cards() {
        this(Collections.emptyList());
    }

    public Cards receiveInitialCards(final List<Card> initialCards) {
        if (initialCards.size() != INITIAL_CARDS_SIZE) {
            throw new IllegalArgumentException("처음 받는 카드는 2장이어야 합니다.");
        }
        List<Card> newCards = new ArrayList<>(initialCards);
        return new Cards(newCards);
    }

    public Cards receiveCard(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Cards(newCards);
    }

    public boolean isBust() {
        return calculateScore().isGreaterThan(BUST_BOUNDARY_EXCLUSIVE);
    }

    public Score calculateScore() {
        boolean hasAce = hasAce();
        Score defaultScore = new Score(calculateDefaultScore());
        boolean canAddBonusScore = defaultScore.add(BONUS_SCORE).isSmallerOrEqualsTo(BUST_BOUNDARY_EXCLUSIVE);

        if (hasAce && canAddBonusScore) {
            defaultScore = defaultScore.add(BONUS_SCORE);
        }
        return defaultScore;
    }

    private int calculateDefaultScore() {
        return cards.stream()
                .mapToInt(Card::getDefaultScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int size() {
        return cards.size();
    }

    public WinningStatus compete(final Cards cards) {
        Score score = this.calculateScore();
        Score dealerScore = cards.calculateScore();

        if (score.isGreaterThan(dealerScore)) {
            return WinningStatus.WIN;
        }
        if (score.isEquals(dealerScore)) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.LOSE;
    }
}
