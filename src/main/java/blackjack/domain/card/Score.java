package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Score {
    ACE(1),
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
    KING(10),
    QUEEN(10),
    ACE_ELEVEN(11);

    private final int amount;

    Score(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static List<Score> getScoreValues() {
        return Arrays.stream(Score.values())
                .filter(score -> score != Score.ACE_ELEVEN)
                .collect(Collectors.toList());
    }
}
