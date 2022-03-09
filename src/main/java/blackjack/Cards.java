package blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cards {
    private static final int SELECT_ACE_VALUE_STANDARD = 11;
    private static final int ADD_BENEFICIAL_VALUE = 10;
    private static final int BASIC_ACE_VALUE = 1;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sum() {
        int sum = cards.stream().mapToInt(card -> card.getValue())
            .reduce(0, Integer::sum);
        if (hasAce()) {
            return selectAceValue(sum);
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
            .map(card -> card.isAce())
            .anyMatch(x -> x.equals(true));
    }

    private int selectAceValue(int sum) {
        if (sum <= SELECT_ACE_VALUE_STANDARD) {
            sum += ADD_BENEFICIAL_VALUE;
        }
        return sum;
    }
}
