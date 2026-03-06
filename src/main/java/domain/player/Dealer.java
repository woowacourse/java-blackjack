package domain.player;

public class Dealer extends Player {
    private static final int DEALER_STOP_SCORE = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean canStand() {
        int score = score();
        if (isBust()) {
            return true;
        }
        return score >= DEALER_STOP_SCORE;
    }

    public String showFirstCard() {
        return handCard.getFirstCardInfo();
    }

}
