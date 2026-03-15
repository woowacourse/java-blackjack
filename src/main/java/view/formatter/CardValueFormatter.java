package view.formatter;

import static exception.ErrorMessage.CARD_VALUE_NOT_EXIST;

import domain.card.CardValue;

public enum CardValueFormatter {
    ACE(CardValue.ACE, "A"),
    TWO(CardValue.TWO, "2"),
    THREE(CardValue.THREE, "3"),
    FOUR(CardValue.FOUR, "4"),
    FIVE(CardValue.FIVE, "5"),
    SIX(CardValue.SIX, "6"),
    SEVEN(CardValue.SEVEN, "7"),
    EIGHT(CardValue.EIGHT, "8"),
    NINE(CardValue.NINE, "9"),
    TEN(CardValue.TEN, "10"),
    JACK(CardValue.JACK, "J"),
    QUEEN(CardValue.QUEEN, "Q"),
    KING(CardValue.KING, "K")
    ;

    private final CardValue cardValue;
    private final String printMessage;

    CardValueFormatter(CardValue cardValue, String printMessage) {
        this.cardValue = cardValue;
        this.printMessage = printMessage;
    }

    public static String from(CardValue cardValue) {
        for (CardValueFormatter cardValueFormatter : CardValueFormatter.values()) {
            if (cardValueFormatter.cardValue == cardValue) {
                return cardValueFormatter.printMessage;
            }
        }
        throw new IllegalStateException(CARD_VALUE_NOT_EXIST.getMessage());
    }
}
