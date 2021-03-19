package blackjack.state;

public class Score {
    public static final int BLACKJACK_SCORE = 21;
    public static final int ACE_CHANGE_NUMBER = 10;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public boolean isChangeAceNumber() {
        return this.value + ACE_CHANGE_NUMBER <= BLACKJACK_SCORE;
    }

    public int aceNumberChange() {
        return value + ACE_CHANGE_NUMBER;
    }

    public int getScore() {
        return value;
    }

    public boolean isBust() {
        return value > BLACKJACK_SCORE;
    }

    public boolean isBlackJack() {
        return value == BLACKJACK_SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;

        Score score1 = (Score) o;

        return value == score1.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
