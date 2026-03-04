package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
