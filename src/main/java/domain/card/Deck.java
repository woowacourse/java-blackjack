package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static final String KOREAN_REGEX = "[가-힣]+";

    private final CardMaker cardMaker;
    private final List<String> alreadyMakeCards;

    public Deck(CardMaker cardMaker) {
        this.cardMaker = cardMaker;
        alreadyMakeCards = new ArrayList<>();
    }

    public String drawCard() {
        String popCardName = cardMaker.makeCard();
        if (!alreadyMakeCards.contains(popCardName)) {
            alreadyMakeCards.add(popCardName);
            return popCardName;
        }
        return drawCard();
    }

    public int extractCardNumber(String card) {
        String cardValue = card.replaceAll(KOREAN_REGEX, "");
        if (SpecialCard.isSpecial(cardValue)) {
            return SpecialCard.convertNumber(cardValue);
        }
        return Integer.parseInt(cardValue);
    }
}
