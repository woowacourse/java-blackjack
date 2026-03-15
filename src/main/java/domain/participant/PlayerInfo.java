package domain.participant;

import java.math.BigDecimal;

public class PlayerInfo {
    private final String name;
    private final Money bettingMoney;

    public PlayerInfo(String name, Money bettingMoney) {
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
