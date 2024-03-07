package blackjack.domain.card;

import java.util.List;

public enum CardScore {
    TWO(List.of(2)),
    THREE(List.of(3)),
    FOUR(List.of(4)),
    FIVE(List.of(5)),
    SIX(List.of(6)),
    SEVEN(List.of(7)),
    EIGHT(List.of(8)),
    NINE(List.of(9)),
    JACK(List.of(10)),
    QUEEN(List.of(10)),
    KING(List.of(10)),
    ACE(List.of(1, 11));

    static final List<CardScore> COURTS = List.of(JACK, QUEEN, KING, ACE);

    private final List<Integer> scores;

    CardScore(List<Integer> scores) {
        this.scores = scores;
    }

    public List<Integer> get() {
        return scores;
    }
}
