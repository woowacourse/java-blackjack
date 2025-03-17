package blackjack.domain.card;

import java.util.Set;

public enum Denomination {

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

    private static final Set<Denomination> SPECIAL_CARD_SCORE = Set.of(Denomination.A, Denomination.K,
            Denomination.Q, Denomination.J);
    private static final int ACE_MAX_NUMBER = 11;

    private final int score;

    Denomination(final int score) {
        this.score = score;
    }

    public String getName() {
        if (SPECIAL_CARD_SCORE.contains(this)) {
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
