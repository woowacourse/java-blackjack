package model.card;

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

    private final List<Integer> scoreData;

    Rank(int score) {
        this.scoreData = List.of(score);
    }

    Rank(List<Integer> score) {
        this.scoreData = score;
    }

    public boolean isMultiScores() {
        return scoreData.size() > 1;
    }

    public int findScore(int idx) {
        return scoreData.get(idx);
    }

    public int findAdjustOrOriginalIdx(int scoreIdx) {
        if (!isLastIdx(scoreIdx)) {
            return scoreIdx + 1;
        }
        return scoreIdx;
    }

    private boolean isLastIdx(int idx) {
        return idx + 1 >= scoreData.size();
    }

    public int getDefaultScore() {
        return scoreData.getFirst();
    }
}
