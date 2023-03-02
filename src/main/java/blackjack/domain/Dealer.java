package blackjack.domain;

public class Dealer extends Participant {
    protected Dealer(final CardPocket cardPocket) {
        super(cardPocket);
    }

    @Override
    public boolean isDrawable() {
        final int currentScore = currentScore();
        return currentScore <= 16;
    }
}
