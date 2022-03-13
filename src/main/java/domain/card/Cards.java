package domain.card;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return unmodifiableList(cards);
    }
}
