package domain.player;

public class Dealer extends Player {

    private static final int DEALER_STOP_SCORE = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean canStand() {
        return isBust() || score() >= DEALER_STOP_SCORE;
    }

    public String firstCard() {
        return handCard.firstCardInfo();
    }

}
