package blackjack.dto;

import blackjack.domain.card.Letter;

import java.util.Arrays;

public enum OutputLetter {

    ACE(Letter.ACE, "A"),
    TWO(Letter.TWO, "2"),
    THREE(Letter.THREE, "3"),
    FOUR(Letter.FOUR, "4"),
    FIVE(Letter.FIVE, "5"),
    SIX(Letter.SIX, "6"),
    SEVEN(Letter.SEVEN, "7"),
    EIGHT(Letter.EIGHT, "8"),
    NINE(Letter.NINE, "9"),
    TEN(Letter.TEN, "10"),
    JACK(Letter.JACK, "J"),
    QUEEN(Letter.QUEEN, "Q"),

    KING(Letter.KING, "K");
    private static final String NON_VALUABLE_MASSAGE = "없는 숫자입니다.";
    private final Letter letter;
    private final String output;

    OutputLetter(Letter letter, String output) {
        this.letter = letter;
        this.output = output;
    }

    public static String match(Letter letter) {
        return Arrays.stream(values())
                .filter(outputLetter -> outputLetter.letter.equals(letter))
                .findFirst().orElseThrow(() -> {
                    throw new IllegalArgumentException(NON_VALUABLE_MASSAGE);
                }).output;
    }
}
