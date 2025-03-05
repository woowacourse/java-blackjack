package blackjack.model;

public abstract class Player {

    protected final Cards cards;
    protected final Role role;

    protected Player(final Role role) {
        this.cards = Cards.empty();
        this.role = role;
    }

    public int calculateSumOfCards() {
        return cards.sumAll();
    }

    public void receiveCards(final Cards cards) {
        this.cards.merge(cards);
    }

    public Cards getCards() {
        return cards;
    }
}
