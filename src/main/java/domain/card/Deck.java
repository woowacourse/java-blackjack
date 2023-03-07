package domain.card;

import java.util.HashSet;
import java.util.Set;

public class Deck {
    private static final String KOREAN_REGEX = "[가-힣]+";

    private static final CardMaker cardMaker = new CardMaker();
    private static final Set<String> alreadyMakeCards = new HashSet<>();

    public static String drawCard() {
        String popCardName = cardMaker.randomMakeCard();
        if (!alreadyMakeCards.contains(popCardName)) {
            alreadyMakeCards.add(popCardName);
            return popCardName;
        }
        return drawCard();
    }

    public static int extractCardNumber(String card) {
        String cardValue = card.replaceAll(KOREAN_REGEX, "");
        return Denomination.convertNumber(cardValue);
    }
}
