package blackjack.model;

public abstract class Player {

    protected final Cards cards;

    protected Player() {
        this.cards = Cards.empty();
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
