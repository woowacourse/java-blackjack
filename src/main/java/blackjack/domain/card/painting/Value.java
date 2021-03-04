package blackjack.domain.card.painting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Value {
    ACE_OF_ONE("A", 1),
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

    private static List<Value> values = Arrays.stream(Value.values())
            .filter(value -> value != ACE_OF_ONE)
            .collect(Collectors.toList());

    private final String letter;
    private final int score;

    Value(String letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public static List<Value> of() {
        return values;
    }

    public int getScore() {
        return score;
    }

    public String getLetter() {
        return letter;
    }
}
