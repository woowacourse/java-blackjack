package blackJack.domain.card;

import java.util.Objects;

public class Score {

    private static final int BLACK_JACK_CARD_COUNT = 2;
    private static final int BLACK_JACK = 21;
    private static final int ACE_BONUS_SCORE = 10;

    private final int score;

    public Score(int cardPoint) {
        score = cardPoint;
    }

    public boolean isBust() {
        return score > BLACK_JACK;
    }

    public boolean isBlackJack(int cardCount) {
        return cardCount == BLACK_JACK_CARD_COUNT && score == BLACK_JACK;
    }

    public boolean hasPossibleAcePoint() {
        return score + ACE_BONUS_SCORE <= BLACK_JACK;
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
}
