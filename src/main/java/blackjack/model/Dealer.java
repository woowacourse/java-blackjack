package blackjack.model;

public class Dealer {

    private final Cards cards;

    public Dealer() {
        this.cards = Cards.empty();
    }

    public void receiveCards(final Cards cards) {
        this.cards.merge(cards);
    }

    public int calculateSumOfCards() {
        return cards.sumAll();
    }

    public Cards getCards() {
        return cards;
    }

}
