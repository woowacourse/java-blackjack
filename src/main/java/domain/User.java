package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private final List<Card> cards = new ArrayList<>();

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
