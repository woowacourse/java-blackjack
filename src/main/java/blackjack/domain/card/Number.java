package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Number {
    ACE("A", 11, 1),
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
    KING("K", 10);

    private static final Map<String, Number> NUMBERS = new HashMap<>();
    private static final String WRONG_NUMBER_EXCEPTION_MESSAGE = "입력된 숫자는 없는 카드 숫자입니다! : %s";

    static {
        for (Number number : values()) {
            NUMBERS.put(number.name, number);
        }
    }

    private final String name;
    private final int score;
    private final int secondScore;

    Number(String name, int score, int secondScore) {
        this.name = name;
        this.score = score;
        this.secondScore = secondScore;
    }

    Number(String name, int score) {
        this(name, score, 0);
    }

    public static Number from(String name) {
        Number number = NUMBERS.get(name);
        if (Objects.isNull(number)) {
            throw new IllegalArgumentException(String.format(WRONG_NUMBER_EXCEPTION_MESSAGE, name));
        }
        return number;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public boolean isAce() {
        return this.equals(ACE);
    }

    public int useSecondScore(int score) {
        return score - this.score + secondScore;
    }
}
