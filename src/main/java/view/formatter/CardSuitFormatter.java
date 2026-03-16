package view.formatter;

import static exception.ErrorMessage.CARD_SUIT_NOT_EXIST;

import domain.card.CardSuit;
import java.util.Arrays;

public enum CardSuitFormatter {
    HEART(CardSuit.HEART, "하트"),
    SPADE(CardSuit.SPADE, "스페이드"),
    CLUB(CardSuit.CLUB, "클럽"),
    DIAMOND(CardSuit.DIAMOND, "다이아몬드")
    ;

    private final CardSuit cardSuit;
    private final String printMessage;

    CardSuitFormatter(CardSuit cardSuit, String printMessage) {
        this.cardSuit = cardSuit;
        this.printMessage = printMessage;
    }

    public static String from(CardSuit cardSuit) {
        return Arrays.stream(CardSuitFormatter.values())
                .filter(cardSuitFormatter -> cardSuitFormatter.cardSuit == cardSuit)
                .map(CardSuitFormatter::getPrintMessage)
                .findAny()
                .orElseThrow(() -> new IllegalStateException(CARD_SUIT_NOT_EXIST.getMessage()));
    }

    public String getPrintMessage() {
        return printMessage;
    }
}
