package blackjack.domain.card;

import java.util.Set;

public enum Rank {
    ONE(new Score(1)),
    TWO(new Score(2)),
    THREE(new Score(3)),
    FOUR(new Score(4)),
    FIVE(new Score(5)),
    SIX(new Score(6)),
    SEVEN(new Score(7)),
    EIGHT(new Score(8)),
    NINE(new Score(9)),
    TEN(new Score(10)),
    JACK(new Score(10)),
    QUEEN(new Score(10)),
    KING(new Score(10)),
    ACE(Set.of(new Score(1), new Score(11)));

    private final Set<Score> scores;

    Rank(Score score) {
        this.scores = Set.of(score);
    }

    Rank(Set<Score> scores) {
        this.scores = scores;
    }

    public Set<Score> getScore() {
        return scores;
    }
}

