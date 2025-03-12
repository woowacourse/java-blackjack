package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Rank {

    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    KING(10),
    JACK(10),
    QUEEN(10),
    ACE(List.of(11, 1));

    private final List<Integer> score;
    private int scoreIdx;

    Rank(int score) {
        this.score = List.of(score);
        this.scoreIdx = 0;
    }

    Rank(List<Integer> score) {
        this.score = score;
    }

    public int adjustRankScore() {
        final List<Integer> sorted = sorted();
        sorted.sort(Collections.reverseOrder());
        if (!isLastValue()) {
            scoreIdx++;
            return sorted.get(scoreIdx);
        }
        return -1;
    }

    private List<Integer> sorted() {
        List<Integer> sorted = new ArrayList<>(score);
        sorted.sort(Collections.reverseOrder());
        return sorted;
    }

    public boolean isLastValue() {
        return scoreIdx + 1 >= score.size();
    }

    public int getRankScore() {
        return score.get(scoreIdx);
    }
}
