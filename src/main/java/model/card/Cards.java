package model.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;
    private boolean hasAce = false;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
        this.hasAce = cards.stream().anyMatch(Card::isAce);
    }

    public void add(Card card) {
        cards.add(card);
        if (card.isAce()) {
            hasAce = true;
        }
    }

    public List<Card> get() {
        return new ArrayList<>(cards);
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public boolean hasAce() {
        return hasAce;
    }

    public Integer getSize() {
        return cards.size();
    }
}
