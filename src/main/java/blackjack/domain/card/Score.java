package blackjack.domain.card;

public class Score {
    public static final Score ACE_ADDITIONAL_SCORE = new Score(10);
    private static final Score MAX_SCORE = new Score(21);

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Score soft() {
        if (!new Score(this.value + ACE_ADDITIONAL_SCORE.value).isBust()) {
            return new Score(this.value + ACE_ADDITIONAL_SCORE.value);
        }
        return new Score(this.value);
    }

    public boolean isBust() {
        return value > MAX_SCORE.value;
    }

    public boolean isMaxScore() {
        return value == MAX_SCORE.value;
    }

    public int toInt() {
        return value;
    }
}
