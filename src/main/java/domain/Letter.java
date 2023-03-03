package domain;

import java.util.Arrays;

public enum Letter {

    LETTER_A("A", 1),
    LETTER_2("2", 2),
    LETTER_3("3", 3),
    LETTER_4("4", 4),
    LETTER_5("5", 5),
    LETTER_6("6", 6),
    LETTER_7("7", 7),
    LETTER_8("8", 8),
    LETTER_9("9", 9),
    LETTER_10("10", 10),
    LETTER_K("K", 10),
    LETTER_Q("Q", 10),
    LETTER_J("J", 10);

    private final String letter;
    private final int score;

    Letter(String letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public static Letter of(String letter) {
        return Arrays.stream(values())
                .filter(letter1 -> letter1.getLetter().equals(letter))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 카드 글자입니다"));
    }

    public boolean isA() {
        return this == LETTER_A;
    }

    public String getLetter() {
        return letter;
    }

    public int getScore() {
        return score;
    }
}
