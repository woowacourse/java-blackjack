package blackjack.model;

public class Dealer extends Player {
    private static final int HITTABLE_THRESHOLD = 16;
    public Dealer(final Cards cards) {
        super("딜러", cards);
    }

    @Override
    public boolean isNotBust() {
        return cards.calculateScore() <= HITTABLE_THRESHOLD;
    }

    public Card openCard() {
        return cards.getFirstCard();
    }
}
