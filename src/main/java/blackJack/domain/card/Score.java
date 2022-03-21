package blackJack.domain.card;

import java.util.Objects;

public class Score implements Comparable<Score> {

    private static final int BLACK_JACK = 21;
    private static final int DEALER_MAXIMUM_RECEIVE_CARD_SCORE = 16;
    private static final int STANDARD_SCORE_OF_CHANGE_ACE = 11;
    private static final int OTHER_POINT_OF_ACE = 11;

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public boolean isBurst() {
        return score > BLACK_JACK;
    }

    public boolean isBlackJack() {
        return score == BLACK_JACK;
    }

    public boolean isDealerCanHit() {
        return score <= DEALER_MAXIMUM_RECEIVE_CARD_SCORE;
    }

    public boolean isChangeAceScore() {
        return score <= STANDARD_SCORE_OF_CHANGE_ACE;
    }

    public Score changeAceScore() {
        return new Score(score + OTHER_POINT_OF_ACE - Denomination.ACE.getPoint());
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.score, o.score);
    }
}
