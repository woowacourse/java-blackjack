package domain;

public class Dealer extends Player {

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isNotBust() {
        return calculateScore() < 17;
    }
}
