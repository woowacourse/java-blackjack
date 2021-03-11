package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Value {
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
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", 11);

    private static final List<Value> values = Arrays.stream(Value.values())
            .collect(Collectors.toList());

    private final String letter;
    private final int score;

    Value(String letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getLetter() {
        return letter;
    }

    public static List<Value> of() {
        return values;
    }
}
