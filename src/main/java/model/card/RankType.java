package model.card;

import java.util.List;

public enum RankType {

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

    RankType(int score) {
        this.score = List.of(score);
    }

    RankType(List<Integer> score) {
        this.score = score;
    }

    public List<Integer> getScore() {
        return score;
    }
}
