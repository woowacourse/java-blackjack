package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final String DUPLICATE_CARD_EXCEPTION_MESSAGE = "Duplicate card exception.";
    private List<Card> cards = new ArrayList<>();
    private boolean ace0;

    public void add(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
        cards.add(card);
    }

    public int getSum() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getValue();
        }
        return addAceWeight(sum);
    }

    private int addAceWeight(int sum) {
        for (Card card : cards) {
            if (sum > 11) {
                break;
            }
            if (card.isAce()) {
                sum += Symbol.getAceWeight();
            }
        }
        return sum;
    }

    public boolean isOverBlackJack() {
        return getSum() > 21;
    }

    public boolean isBlackJack() {
        return getSum() == 21;
    }
}
