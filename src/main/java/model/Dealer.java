package model;

public class Dealer extends Participant{
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }
}
