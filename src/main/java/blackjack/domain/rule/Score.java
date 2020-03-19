package blackjack.domain.rule;

import java.util.HashMap;
import java.util.Map;

public class Score {

    private static final int BUSTED = 0;
    private static final int BLACKJACK = 21;
    private static final Map<Integer, Score> SCORE_MATCHER;

    private int score;

    static {
        SCORE_MATCHER = new HashMap<>();
        for (int i = BUSTED; i <= BLACKJACK; i++) {
            SCORE_MATCHER.put(i, new Score(i));
        }
    }

    private Score(int score) {
        this.score = score;
    }

    public static Score from(int score) {
        if (score > BLACKJACK) {
            return SCORE_MATCHER.get(BUSTED);
        }
        return SCORE_MATCHER.get(score);
    }

    public boolean isBusted() {
        return score == BUSTED;
    }

    public boolean isMaxValue() {
        return score == BLACKJACK;
    }

    public int getScore() {
        return score;
    }
}
