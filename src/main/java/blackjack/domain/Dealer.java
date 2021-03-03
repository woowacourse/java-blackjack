package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private static final int DRAW_BOUND = 16;
    private static final int BLACKJACK = 21;
    private static final int ACE_DIFF = 10;

    private final List<Card> cards;

    public Dealer() {
        cards = new ArrayList<>();
    }

    public void drawCard(final Card card) {
        cards.add(card);
    }

    public boolean isCanDraw() {
        return getResult() <= DRAW_BOUND;
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
}
