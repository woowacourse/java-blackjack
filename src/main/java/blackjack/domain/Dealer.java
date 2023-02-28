package blackjack.domain;

public class Dealer {

    private final Cards cards;

    public Dealer(final Cards cards) {
        this.cards = cards;
    }

    public Cards getCards() {
        return cards;
    }
}
