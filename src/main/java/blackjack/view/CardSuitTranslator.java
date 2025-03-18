package blackjack.view;

import blackjack.common.ErrorMessage;
import blackjack.domain.CardSuit;
import java.util.Map;

public class CardSuitTranslator {

    private static final Map<CardSuit, String> suitDescriptions = Map.of(
            CardSuit.HEART, "하트",
            CardSuit.DIAMOND, "다이아몬드",
            CardSuit.CLUB, "클로버",
            CardSuit.SPADE, "스페이드"
    );

    private CardSuitTranslator() {
    }

    public static String getDescription(CardSuit suit) {
        if (!suitDescriptions.containsKey(suit)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TRANSLATE.getMessage());
        }

        return suitDescriptions.get(suit);
    }
}
