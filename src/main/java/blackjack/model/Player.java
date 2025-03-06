package blackjack.model;

import java.util.List;

public abstract class Player {

    protected final Cards cards;
    protected final Role role;

    protected Player(final Role role) {
        this.cards = Cards.empty();
        this.role = role;
    }

    public List<Integer> calculateSumOfCards() {
        return cards.sumAll();
    }

    public void receiveCards(final Cards cards) {
        this.cards.merge(cards);
    }

    public boolean hasRole(final Role role) {
        return this.role == role;
    }

    public Cards getCards() {
        return cards;
    }
}
