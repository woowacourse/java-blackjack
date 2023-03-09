package domain;

import java.util.HashMap;
import java.util.Map;

public class Score {

    private static final int MAX_SCORE = 21;
    private static final int DEALER_CARD_ABLE_BOUND = 16;
    private static final int PLAYER_CARD_ABLE_BOUND = 20;
    private static final Map<Integer, Score> scoreFactory = new HashMap<>();

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(int score) {
        return scoreFactory.computeIfAbsent(score, ignored -> new Score(score));
    }

    public boolean isMaxScore() {
        return score == MAX_SCORE;
    }

    public boolean isBust() {
        return MAX_SCORE < score;
    }

    public boolean isDealerMoreCardAble() {
        return score <= DEALER_CARD_ABLE_BOUND;
    }

    public boolean isPlayerMoreCardAble() {
        return score <= PLAYER_CARD_ABLE_BOUND;
    }

}
