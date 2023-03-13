package domain.state;

public abstract class Running implements State {
    private final Hand hand;

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
}
