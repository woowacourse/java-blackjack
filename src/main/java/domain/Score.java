package domain;

import java.util.HashMap;
import java.util.Map;

public class Score {

    private static final int MAX_SCORE = 21;
    private static final Map<Integer, Score> scoreFactory = new HashMap<>();

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(int score) { // 캐싱해둔 것 반환
        return scoreFactory.computeIfAbsent(score, ignored -> new Score(score));
    }

    public boolean isMaxScore() {
        return score == MAX_SCORE;
    }

    public boolean isBust() {
        return MAX_SCORE < score;
    }

}
