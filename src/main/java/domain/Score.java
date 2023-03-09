package domain;

import java.util.HashMap;
import java.util.Map;

public class Score {

    private static final int MAX_SCORE = 21;
    private static final int DEALER_CARD_ABLE_BOUND = 16;
    private static final int PLAYER_CARD_ABLE_BOUND = 20;
    private static final Map<Integer, Score> scoreFactory = new HashMap<>();

    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score of(int inputValue, int limit, int aceCount) {
        int value = decreaseScoreByAceCount(inputValue, limit, aceCount);
        return scoreFactory.computeIfAbsent(value, ignored -> new Score(value));
    }

    private static int decreaseScoreByAceCount(int inputValue, int limit, int aceCount) {
        while (canDecreaseScore(inputValue, limit, aceCount)) {
            inputValue -= 10;
            aceCount--;
        }

        return inputValue;
    }

    private static boolean canDecreaseScore(int inputValue, int limit, int aceCount) {
        return inputValue != MAX_SCORE && aceCount != 0 && limit < inputValue;
    }

    public boolean isMaxScore() {
        return value == MAX_SCORE;
    }

    public boolean isBust() {
        return MAX_SCORE < value;
    }

    public boolean isDealerMoreCardAble() {
        return value <= DEALER_CARD_ABLE_BOUND;
    }

    public boolean isPlayerMoreCardAble() {
        return value <= PLAYER_CARD_ABLE_BOUND;
    }

    public int getValue() {
        return value;
    }

}
