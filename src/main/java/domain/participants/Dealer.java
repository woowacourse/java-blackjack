package domain.participants;

import domain.BettingMoney;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int BOUND = 16;

    public Dealer() {
        super(NAME, new BettingMoney(0));
    }

    public boolean isOverDealerStandard() {
        return super.getCardsSum() > BOUND;
    }

    public boolean dealerWin(int playerSum){
        return this.getCardsSum() > playerSum;
    }
}
