package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int ACE_DIFF = 10;
    private static final int BLACKJACK = 21;

    private final List<Card> cards;

    public Player() {
        cards = new ArrayList<>();
    }

    public void drawCard(final Card card) {
        cards.add(card);
    }

    public int getResult() {
        int lowValue = getLowValue();
        int highValue = getHighValue(lowValue);
        if (highValue > BLACKJACK) {
            return lowValue;
        }
        return highValue;
    }

    private int getLowValue() {
        return cards.stream()
            .mapToInt(card->card.getCardNumber().getValue())
            .sum();
    }

    private int getHighValue(int lowValue) {
        int highValue = lowValue;
        if (cards.stream().anyMatch(card -> card.getCardNumber() == CardNumber.ACE)) {
            highValue += ACE_DIFF;
        }
        return highValue;
    }

    public boolean isCanDraw() {
        return getResult() <= BLACKJACK;
    }
}
