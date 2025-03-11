package blackjack.view;

import blackjack.domain.card.CardNumber;

public enum CardNumberView {
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
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String message;

    CardNumberView(String message) {
        this.message = message;
    }

    public static String getNumberMessage(final CardNumber cardNumber) {
        return CardNumberView.valueOf(cardNumber.name()).message;
    }
}
