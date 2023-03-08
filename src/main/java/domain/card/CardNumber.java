package domain.card;

import java.util.Arrays;
import java.util.List;

public enum CardNumber {

    ACE(Score.create(1)),
    TWO(Score.create(2)),
    THREE(Score.create(3)),
    FOUR(Score.create(4)),
    FIVE(Score.create(5)),
    SIX(Score.create(6)),
    SEVEN(Score.create(7)),
    EIGHT(Score.create(8)),
    NINE(Score.create(9)),
    TEN(Score.create(10)),
    KING(Score.create(10)),
    QUEEN(Score.create(10)),
    JACK(Score.create(10));

    private final Score score;

    CardNumber(final Score score) {
        this.score = score;
    }

    public static List<CardNumber> getAll() {
        return Arrays.asList(values());
    }

    boolean isAce() {
        return this == ACE;
    }

    public Score getScore() {
        int score = this.score.getScore();
        return Score.create(score);
    }
}
