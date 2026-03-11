package model;

public class Dealer extends AbstractParticipant {

    private static final String DEALER_NAME = "딜러";
    private static final int MINIMUM_STAND_SCORE = 16;
    private int dealerProfit;

    public Dealer() {
        super(DEALER_NAME);
        this.dealerProfit = 0;
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() <= MINIMUM_STAND_SCORE;
    }

    public int getDealerProfit() {
        return this.dealerProfit;
    }

    public void addDealerProfit(int dealerProfit) {
        this.dealerProfit += dealerProfit;
    }

    public void subtractDealerProfit(int dealerProfit) {
        this.dealerProfit -= dealerProfit;
    }
}
