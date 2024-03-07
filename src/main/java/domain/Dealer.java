package domain;

public class Dealer extends Player{
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean alive() {
        return calculateScore() < 17;
    }
}
