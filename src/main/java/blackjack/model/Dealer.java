package blackjack.model;

public class Dealer extends Player {
    public Dealer(final Cards cards) {
        super("딜러", cards);
    }

    public boolean canReceive() {
        if (cards.calculateScore() <= 16) {
            return true;
        }
        return false;
    }

    public Deck openCard() {
        return cards.getFirstCard();
    }
}
