package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final String DUPLICATE_CARD_EXCEPTION_MESSAGE = "Duplicate card exception.";
    private List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
        cards.add(card);
    }
}
