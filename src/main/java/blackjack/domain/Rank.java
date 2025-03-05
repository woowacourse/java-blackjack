package blackjack.domain;

import java.util.List;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(List.of(1, 11));

    private final List<Integer> scores;

    Rank(int score) {
        this.scores = List.of(score);
    }

    Rank(List<Integer> scores) {
        this.scores = scores;
    }

    public List<Integer> getScore() {
        return scores;
    }
}

