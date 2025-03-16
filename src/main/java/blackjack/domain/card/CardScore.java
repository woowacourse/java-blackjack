package blackjack.domain.card;

import java.util.List;

public enum CardScore {

    A(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    K(10),
    Q(10),
    J(10);

    private static final List<CardScore> SPECIAL_CARD_SCORE = List.of(CardScore.A, CardScore.K,
            CardScore.Q, CardScore.J);
    private static final int ACE_MAX_NUMBER = 11;

    private final int score;

    CardScore(final int score) {
        this.score = score;
    }

    public String getName() {
        final boolean isSpecialCardScore = SPECIAL_CARD_SCORE.stream()
                .anyMatch(cardScore -> cardScore == this);
        if (isSpecialCardScore) {
            return this.name();
        }
        return String.valueOf(score);
    }

    public int getMinNumber() {
        return score;
    }

    public int getMaxNumber() {
        if (this == A) {
            return ACE_MAX_NUMBER;
        }
        return score;
    }
}
