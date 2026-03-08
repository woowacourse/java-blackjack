package domain;

public class Dealer extends Player {

    public static final int MAX_DEALER_SHOULD_RECEIVE_SCORE = 16;

    public Dealer() {
        super("딜러", new Hand());
    }

    @Override
    public boolean canReceiveCard() {
        return getScore() <= MAX_DEALER_SHOULD_RECEIVE_SCORE;
    }
}
