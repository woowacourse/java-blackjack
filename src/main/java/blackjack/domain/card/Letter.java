package blackjack.domain.card;

import java.util.Arrays;

public enum Letter {

    ACE("A", 11),
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

    private static final String INVALID_NAME_ERROR_MESSAGE = "해당 이름을 가지는 글자가 존재하지 않습니다.";

    private final String name;
    private final int value;

    Letter(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public static Letter from(final String name) {
        return Arrays.stream(Letter.values())
                .filter(letter -> letter.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_NAME_ERROR_MESSAGE));
    }

    public boolean isAce() {
        return this.equals(ACE);
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
