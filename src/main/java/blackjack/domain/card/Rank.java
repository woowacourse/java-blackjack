package blackjack.domain.card;

import java.util.Set;

public enum Rank {
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    ACE(Set.of(1, 11), "A");

    private final Set<Integer> scores;
    private final String description;

    Rank(int score, String description) {
        this.scores = Set.of(score);
        this.description = description;
    }

    Rank(Set<Integer> scores, String descriptions) {
        this.scores = scores;
        this.description = descriptions;
    }

    public Set<Integer> getScores() {
        return scores;
    }

    public String getDescription() {
        return description;
    }
}

