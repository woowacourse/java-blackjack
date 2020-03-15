package domain;

import domain.card.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Score implements Comparable<Score> {
    private static final int ACE_BONUS = 10;
    private static final int BLACKJACK_MAX_NUMBER = 21;
    private static final int INIT_SCORE = 0;
    private static final Map<Integer, Score> cache = new HashMap<>();

    static {
        for (int i = INIT_SCORE; i <= BLACKJACK_MAX_NUMBER; i++) {
            cache.put(i, new Score(i));
        }
    }

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score valueOf(int score) {
        validatePositive(score);
        if (score <= BLACKJACK_MAX_NUMBER) {
            return cache.get(score);
        }
        return new Score(score);
    }

    private static void validatePositive(int score) {
        if (score < INIT_SCORE) {
            throw new NegativeArraySizeException("Score 값은 양수이어야 합니다.");
        }
    }

    public static Score valueOf(List<Card> cards) {
        if (Objects.isNull(cards)) {
            throw new IllegalArgumentException("null값이 입력되었습니다.");
        }
        return cards.stream()
                .reduce(sumScore(cards), Score::addAceBonus, Score::sum);
    }

    private static Score sumScore(List<Card> cards) {
        return cards.stream()
                .reduce(new Score(INIT_SCORE), Score::add, Score::sum);
    }

    private static Score sum(Score score1, Score score2) {
        return Score.valueOf(score1.score + score2.score);
    }

    private Score add(Card card) {
        return Score.valueOf(score + card.getValue());
    }

    private Score addAceBonus(Card card) {
        if (card.isAce() && canAddAceBonus()) {
            return Score.valueOf(score + ACE_BONUS);
        }
        return this;
    }

    private boolean canAddAceBonus() {
        return score + ACE_BONUS <= BLACKJACK_MAX_NUMBER;
    }

    public boolean isBust() {
        return score > BLACKJACK_MAX_NUMBER;
    }

    public boolean isNotBust() {
        return score <= BLACKJACK_MAX_NUMBER;
    }

    public boolean isBlackJackScore() {
        return score == BLACKJACK_MAX_NUMBER;
    }

    public boolean isNotBlackJackScore() {
        return score != BLACKJACK_MAX_NUMBER;
    }

    public boolean isLessThen(int comparedNumber) {
        return score <= comparedNumber;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public int compareTo(Score score1) {
        return Integer.compare(score, score1.score);
    }
}
