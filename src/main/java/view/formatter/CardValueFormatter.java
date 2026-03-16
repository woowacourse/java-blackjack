package view.formatter;

import static exception.ErrorMessage.CARD_VALUE_NOT_EXIST;

import domain.card.CardScore;
import java.util.Arrays;

public enum CardValueFormatter {
    ACE(CardScore.ACE, "A"),
    TWO(CardScore.TWO, "2"),
    THREE(CardScore.THREE, "3"),
    FOUR(CardScore.FOUR, "4"),
    FIVE(CardScore.FIVE, "5"),
    SIX(CardScore.SIX, "6"),
    SEVEN(CardScore.SEVEN, "7"),
    EIGHT(CardScore.EIGHT, "8"),
    NINE(CardScore.NINE, "9"),
    TEN(CardScore.TEN, "10"),
    JACK(CardScore.JACK, "J"),
    QUEEN(CardScore.QUEEN, "Q"),
    KING(CardScore.KING, "K")
    ;

    private final CardScore cardScore;
    private final String printMessage;

    CardValueFormatter(CardScore cardScore, String printMessage) {
        this.cardScore = cardScore;
        this.printMessage = printMessage;
    }

    public static String from(CardScore cardScore) {
        return Arrays.stream(CardValueFormatter.values())
                .filter(cardValueFormatter -> cardValueFormatter.cardScore == cardScore)
                .map(CardValueFormatter::getPrintMessage)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(CARD_VALUE_NOT_EXIST.getMessage()));
    }

    public String getPrintMessage() {
        return printMessage;
    }
}
