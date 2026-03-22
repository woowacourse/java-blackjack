package view.formatter;

import domain.card.CardSuit;
import java.util.Arrays;

public enum CardSuitFormatter {
    HEART(CardSuit.HEART, "하트"),
    SPADE(CardSuit.SPADE, "스페이드"),
    CLUB(CardSuit.CLUB, "클럽"),
    DIAMOND(CardSuit.DIAMOND, "다이아몬드"),
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
                .orElseThrow(() -> new IllegalStateException("ERROR] 존재하지 않는 카드 문양입니다."));
    }

    public String getPrintMessage() {
        return printMessage;
    }
}
