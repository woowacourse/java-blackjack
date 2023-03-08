package view;

import domain.type.Letter;

public enum LetterView {

    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    KING("K"),
    QUEEN("Q"),
    JACK("J");

    private final String sign;

    LetterView(final String sign) {
        this.sign = sign;
    }

    public static LetterView from(final Letter letter) {
        return LetterView.valueOf(letter.name());
    }

    public String getSign() {
        return sign;
    }
}
