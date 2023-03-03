package domain;

import java.util.Arrays;

public enum Letter {

    LETTER_A("A"),
    LETTER_2("2"),
    LETTER_3("3"),
    LETTER_4("4"),
    LETTER_5("5"),
    LETTER_6("6"),
    LETTER_7("7"),
    LETTER_8("8"),
    LETTER_9("9"),
    LETTER_10("10"),
    LETTER_K("K"),
    LETTER_Q("Q"),
    LETTER_J("J");

    private final String letter;

    Letter(String letter) {
        this.letter = letter;
    }

    public static Letter of(String letter) {
        return Arrays.stream(values())
                .filter(letter1 -> letter1.getLetter().equals(letter))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 카드 글자입니다"));
    }

    public boolean isNotA() {
        return this != LETTER_A;
    }

    public String getLetter() {
        return letter;
    }
}
