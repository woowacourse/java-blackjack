package blackjack.domain.card;

import java.util.Set;

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
    ACE(Set.of(1, 11));

    private final Set<Integer> scores;

    Rank(int score) {
        this.scores = Set.of(score);
    }

    Rank(Set<Integer> scores) {
        this.scores = scores;
    }

    public Set<Integer> getScore() {
        return scores;
    }
}

