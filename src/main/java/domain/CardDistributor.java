package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDistributor {

    private final List<Card> cards;

    public CardDistributor(List<Card> cards) {
        this.cards = cards;
        shuffle(cards);
    }

    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public Card distribute() {
        return cards.remove(cards.size() - 1);
    }

    public List<Card> distributeInitialCard() {
        return new ArrayList<>() {{
            add(distribute());
            add(distribute());
        }};
    }

    public int getDeckSize() {
        return cards.size();
    }

}
