package blackjack.domain.card;

import java.util.Arrays;

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

    private static final String WRONG_NUMBER_EXCEPTION_MESSAGE = "입력된 숫자는 없는 카드 숫자입니다! : %s";

    private final String name;
    private final int score;
    private final int secondScore;

    Number(final String name, final int score, final int secondScore) {
        this.name = name;
        this.score = score;
        this.secondScore = secondScore;
    }

    Number(final String name, final int score) {
        this(name, score, score);
    }

    public static Number from(final String name) {
        return Arrays.stream(values())
                .filter(number -> number.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(WRONG_NUMBER_EXCEPTION_MESSAGE, name)));
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

    public int useSecondScore(final int score) {
        return score - this.score + secondScore;
    }
}
