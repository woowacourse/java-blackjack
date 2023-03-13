package domain.state;

public class Bust extends Finished {

    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }
}
