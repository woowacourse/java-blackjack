package blackjack.domain.card;

import java.util.Set;
import java.util.stream.Collectors;

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

    private final Set<Score> scores;

    Rank(int score) {
        this.scores = Set.of(new Score(score));
    }

    Rank(Set<Integer> scores) {
        this.scores = scores.stream()
                .map(Score::new)
                .collect(Collectors.toSet());
    }

    public Set<Score> getScore() {
        return scores;
    }
}

