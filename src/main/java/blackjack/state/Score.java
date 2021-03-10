package blackjack.state;

public class Score {
    public static final int BLACKJACK_SCORE = 21;
    public static final int ACE_CHANGE_NUMBER = 10;

    private final int score;

    public Score(int score) {
        this.score = score;
    }

    public boolean isChangeAceNumber() {
        return this.score + ACE_CHANGE_NUMBER <= BLACKJACK_SCORE;
    }

    public int aceNumberChange() {
        return score + ACE_CHANGE_NUMBER;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;

        Score score1 = (Score) o;

        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return score;
    }
}
