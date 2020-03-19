package domain.result;

import domain.gamer.Money;
import domain.gamer.Name;

public class Result {
    Name gamerName;
    Money profit;

    public Result(Name gamerName, Money profit) {
        this.gamerName = gamerName;
        this.profit = profit;
    }

    public Name getGamerName() {
        return gamerName;
    }

    public Money getProfit() {
        return profit;
    }
}
