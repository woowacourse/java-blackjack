package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(final Card card) {
        cards.add(card);
    }
    
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
