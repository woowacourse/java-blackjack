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
    private int scoreIdx;

    Rank(int score) {
        this.scoreData = List.of(score);
        this.scoreIdx = 0;
    }

    Rank(List<Integer> score) {
        this.scoreData = score;
    }

    public void adjustRankScore() {
        if (!isLastValue()) {
            scoreIdx++;
        }
    }

    public boolean isLastValue() {
        return scoreIdx + 1 >= scoreData.size();
    }

    public int getScore() {
        return scoreData.get(scoreIdx);
    }
}
