package blackjack.domain;

import java.util.Arrays;

public enum CardNumber {
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

    private final String number;
    private final int score;

    CardNumber(String number, int score) {
        this.number = number;
        this.score = score;
    }

    public static int scoreByNumber(String input) {
        return Arrays.stream(values())
                .filter(cardNumber -> cardNumber.number.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("숫자를 찾을 수 없습니다."))
                .getScore();
    }

    public String getNumber() {
        return number;
    }

    public int getScore() {
        return score;
    }
}
