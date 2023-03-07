package blackjack.view;

import java.util.Arrays;

public enum OutputLetter {

    ACE("A", "A"),
    TWO("2", "2"),
    THREE("3", "3"),
    FOUR("4", "4"),
    FIVE("5", "5"),
    SIX("6", "6"),
    SEVEN("7", "7"),
    EIGHT("8", "8"),
    NINE("9", "9"),
    TEN("10", "10"),
    JACK("J", "J"),
    QUEEN("Q", "Q"),

    KING("K", "K");
    private final String letter;
    private final String output;

    OutputLetter(String letter, String output) {
        this.letter = letter;
        this.output = output;
    }

    public static String match(String letter) {
        return Arrays.stream(values())
                .filter(outputLetter -> outputLetter.isSame(letter))
                .findFirst()
                .get().output;
    }

    private boolean isSame(String letter) {
        return this.letter.equals(letter);
    }
}
