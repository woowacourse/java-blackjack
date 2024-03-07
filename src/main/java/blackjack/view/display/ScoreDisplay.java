package blackjack.view.display;

import blackjack.model.Score;
import java.util.Arrays;

public enum ScoreDisplay {
    ACE(Score.ACE, "A"),
    TWO(Score.TWO, "2"),
    THREE(Score.THREE, "3"),
    FOUR(Score.FOUR, "4"),
    FIVE(Score.FIVE, "5"),
    SIX(Score.SIX, "6"),
    SEVEN(Score.SEVEN, "7"),
    EIGHT(Score.EIGHT, "8"),
    NINE(Score.NINE, "9"),
    TEN(Score.TEN, "10"),
    KING(Score.KING, "K"),
    QUEEN(Score.QUEEN, "Q"),
    JACK(Score.JACK, "J"),
    ;

    private final Score score;
    private final String value;

    ScoreDisplay(final Score score, final String value) {
        this.score = score;
        this.value = value;
    }

    public static String getValue(final Score score) {
        return Arrays.stream(ScoreDisplay.values())
                .filter(scoreDisplay -> scoreDisplay.score == score)
                .findFirst()
                .get()
                .value;
    }
}
