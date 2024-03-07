package blackjack.domain.card;

import java.util.List;

public enum CardScores {
    TWO(List.of(2)),
    THREE(List.of(3)),
    FOUR(List.of(4)),
    FIVE(List.of(5)),
    SIX(List.of(6)),
    SEVEN(List.of(7)),
    EIGHT(List.of(8)),
    NINE(List.of(9)),
    J(List.of(10)),
    Q(List.of(10)),
    K(List.of(10)),
    A(List.of(1, 11));

    static final List<CardScores> COURTS = List.of(J, Q, K, A);
    
    private final List<Integer> scores;

    CardScores(List<Integer> scores) {
        this.scores = scores;
    }

    public List<Integer> get() {
        return scores;
    }
}
