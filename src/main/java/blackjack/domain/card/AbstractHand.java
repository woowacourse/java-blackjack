package blackjack.domain.card;

public abstract class AbstractHand implements Hand {
    private final Cards cards;

    protected AbstractHand(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public final Cards cards() {
        return cards;
    }

    @Override
    public final int score() {
        return cards.totalScore();
    }
}
