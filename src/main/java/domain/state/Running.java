package domain.state;

public abstract class Running implements State {
    protected final Hand hand;

    public Running(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public Score calculateScore() {
        return hand.calculateScore();
    }
}
