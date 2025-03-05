package blackjack.model;

public class Player {

    private final Cards cards;

    public Player() {
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
