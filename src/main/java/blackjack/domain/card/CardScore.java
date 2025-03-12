package blackjack.domain.card;

import java.util.List;

public enum CardScore {

    A(List.of(1, 11)),
    TWO(List.of(2)),
    THREE(List.of(3)),
    FOUR(List.of(4)),
    FIVE(List.of(5)),
    SIX(List.of(6)),
    SEVEN(List.of(7)),
    EIGHT(List.of(8)),
    NINE(List.of(9)),
    TEN(List.of(10)),
    K(List.of(10)),
    Q(List.of(10)),
    J(List.of(10));

    private static final List<CardScore> SPECIAL_CARD_SCORE = List.of(CardScore.A, CardScore.K,
            CardScore.Q, CardScore.J);

    private final List<Integer> scores;

    CardScore(final List<Integer> scores) {
        this.scores = scores;
    }

    public String getName() {
        final boolean isSpecialCardScore = SPECIAL_CARD_SCORE.stream()
                .anyMatch(cardScore -> cardScore == this);
        if (isSpecialCardScore) {
            return this.name();
        }
        return String.valueOf(scores.getFirst());
    }

    public int getMinNumber() {
        return scores.getFirst();
    }

    public int getMaxNumber() {
        return scores.getLast();
    }
}
