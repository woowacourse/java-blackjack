package domain.betting;

import java.math.BigDecimal;

public class BettingLog {
    private final String name;
    private final BettingMoney bettingMoney;

    public BettingLog(String name, BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }
}
