package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    protected List<Card> cards;

    public Player(Card... cards) {
        this.cards = new ArrayList<>();
        Collections.addAll(this.cards, cards);
    }

    protected int sumCardNumber() {
        return (int) this.cards.stream()
                .mapToLong(Card::getCardNumber)
                .sum();
    }

    abstract void insertCard(Cards cards);
}
