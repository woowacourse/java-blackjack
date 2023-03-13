package domain;

import java.util.Map;

public class BettingAmount {

    private final Map<Player, Amount> bettingAmount;

    public BettingAmount(Map<Player, Amount> bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public Map<Player, Amount> getBettingAmount() {
        return bettingAmount;
    }

}
