package domain.result;

import domain.gamer.Money;
import domain.gamer.Name;

public class Result {
    Name gamerName;
    Money bettingMoney;

    public Result(Name gamerName, Money bettingMoney) {
        this.gamerName = gamerName;
        this.bettingMoney = bettingMoney;
    }

    public Name getGamerName() {
        return gamerName;
    }

    public Money getBettingMoney() {
        return bettingMoney;
    }
}
