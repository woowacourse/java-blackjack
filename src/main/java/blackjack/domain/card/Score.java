package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Score {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    KING("K", 10),
    QUEEN("Q", 10),
    ACE_ELEVEN("A", 11);

    private final String symbol;
    private final int amount;

    Score(final String symbol, final int amount) {
        this.symbol = symbol;
        this.amount = amount;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public int getAmount() {
        return this.amount;
    }

    public static List<Score> getScoreValues() {
        return Arrays.stream(Score.values())
                .filter(score -> score != Score.ACE_ELEVEN)
                .collect(Collectors.toList());
    }
}
