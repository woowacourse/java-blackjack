package blackjack.view.display.deck;

import blackjack.model.deck.Score;
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
    private final String displayName;

    ScoreDisplay(final Score score, final String displayName) {
        this.score = score;
        this.displayName = displayName;
    }

    public static String getValue(final Score score) {
        return Arrays.stream(ScoreDisplay.values())
                .filter(scoreDisplay -> scoreDisplay.score == score)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 카드 점수에 매칭되는 출력 문자가 없습니다."))
                .displayName;
    }
}
