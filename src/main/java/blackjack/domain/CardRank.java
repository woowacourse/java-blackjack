package blackjack.domain;

import java.util.Set;

public enum CardRank {
    ACE(Set.of(1, 11), "A"),
    TWO(Set.of(2), "2"),
    THREE(Set.of(3), "3"),
    FOUR(Set.of(4), "4"),
    FIVE(Set.of(5), "5"),
    SIX(Set.of(6), "6"),
    SEVEN(Set.of(7), "7"),
    EIGHT(Set.of(8), "8"),
    NINE(Set.of(9), "9"),
    TEN(Set.of(10), "10"),
    JACK(Set.of(10), "J"),
    QUEEN(Set.of(10), "Q"),
    KING(Set.of(10), "K");

    private final Set<Integer> score;
    private final String name;

    CardRank(Set<Integer> score, String name) {
        this.score = score;
        this.name = name;
    }

    public Set<Integer> getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
